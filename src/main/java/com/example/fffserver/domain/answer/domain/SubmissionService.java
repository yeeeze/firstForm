package com.example.fffserver.domain.answer.domain;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.controller.SseEmitters;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.vo.Event;
import com.example.fffserver.domain.form.domain.vo.EventFactory;
import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SubmissionService {

    // TODO : 유저들의 정보인 List<Answer>을 자바 메모리 상에 저장하지 않고 외부의 메세지큐에 저장하기 (refactor)
    // TODO : 같은 IP의 다수 브라우저탭으로 동시접속 불가 처리

    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = -1;
    // key -> formId(event) + userName(threadName)
    private final Map<String, List<Answer>> userAnswerMap = new ConcurrentHashMap<>();

    private final EventFactory eventFactory;
    private final AnswerService answerService;
    private final FormService formService;
    private final RedisTemplate<String, String> redisTemplate;
    private final SseEmitters sseEmitters;

    public SubmissionService(EventFactory eventFactory, AnswerService answerService, FormService formService, RedisTemplate<String, String> redisTemplate, SseEmitters sseEmitters) {
        this.eventFactory = eventFactory;
        this.answerService = answerService;
        this.formService = formService;
        this.redisTemplate = redisTemplate;
        this.sseEmitters = sseEmitters;
    }

    // 대기열, 이벤트 추가
    public Long addQueue(String userId, Event event, List<Answer> answerList) {
        if (!isEnd(event)) {
            final long now = System.currentTimeMillis();

            redisTemplate.opsForZSet().add(event.getFormId().toString(), userId, now);
            userAnswerMap.put(event.getFormId().toString() + userId, answerList);
            log.info("이벤트명: {}, addQueue() 완료 : {} {} ({}초)", event.getFormId().toString(), userId, Thread.currentThread().getName(), now);
            Long rank = redisTemplate.opsForZSet().rank(event.getFormId().toString(), userId);
            sendToClient(userId, "현재 대기번호는 " + rank + "입니다.\n");
            return rank;
        } else {
            throw new BusinessException(ExceptionCode.AFTER_END);
        }
    }


    // 대기열 순번 안내
    public void getOrder() {
        eventFactory.events().parallelStream().forEach(objectIdEventEntry -> {
            String eventId = objectIdEventEntry.getValue().getFormId().toString();

            if (isEnd(objectIdEventEntry.getValue())) {
                return;
            }

            Set<String> waitingQueue = redisTemplate.opsForZSet().range(eventId, FIRST_INDEX, LAST_INDEX);
            if (waitingQueue == null) {
                return;
            }
            for (String userId : waitingQueue) {
                Long rank = redisTemplate.opsForZSet().rank(eventId, userId);
                log.info("{} 이벤트의 {}님의 현재 대기번호는 {}입니다.",eventId, userId, rank);
                sendToClient(userId, "현재 대기번호는 " + rank + "입니다.\n");
            }
        });
    }

    // 1초 마다 대기열 -> 참가열 이동 후
    // DB insert
    // 대기열, 질문 map에서 삭제
    public void enter() {
        eventFactory.events().stream()
                .map(Map.Entry::getValue)
                .parallel()
                .forEach(event -> {
                    String eventId = event.getFormId().toString();
                    int limitCount = event.getLimitCount();

                    Set<String> waitingQueue = redisTemplate.opsForZSet().range(eventId, FIRST_INDEX, limitCount);
                    if (waitingQueue == null) {
                        return;
                    }
                    for (String userId : waitingQueue) {
                        if (isEnd(event)) {
                            formService.changeEndTime(LocalDateTime.now(), eventId);
                            eventFactory.removeEvent(event.getFormId());
                            redisTemplate.expire(event.getFormId().toString(), 3, TimeUnit.DAYS);
                            return;
                        }
                        event.decrease();
                        redisTemplate.opsForZSet().remove(eventId, userId);
                        List<Answer> answerList = userAnswerMap.get(eventId + userId);
                        answerService.mappedQuestionAndInsert(answerList);
                        log.info("{}님의 응답 제출이 완료됐습니다. 이벤트: {}, 응답: {}", userId, eventId, answerList);
                        sendToClient(userId, "응답 제출이 완료됐습니다. 감사합니다.");
                    }
                });
    }

    // 선착순 종료 체크
    private boolean isEnd(Event event) {
        if (event.isEnd()) {
            log.info("==== {}의 선착순이 마감되었습니다. ====", event.getFormId().toString());
            Set<String> waitingQueue = redisTemplate.opsForZSet().range(event.getFormId().toString(), FIRST_INDEX, LAST_INDEX);

            if (waitingQueue == null) {
                return true;
            }

            for (String userId : waitingQueue) {
                log.info("{}님에게 마감 소식 알림", userId);
                sendToClient(userId, "==== " + event.getFormId().toString() + "의 선착순이 마감되었습니다. ====");
            }
            return true;
        }

        return false;
    }

    // 남아있는 대기열 유저 숫자 확인
    public Long getWaitingQueueSize(Event event) {
        return redisTemplate.opsForZSet().size(event.getFormId().toString());
    }

    public void resendLastEvent(String userId, String lastEventData) {
        sendToClient(userId, lastEventData);
    }

    private void sendToClient(String userId, String data) {
        sseEmitters.setLastEvent(userId, data);

        Map<String, SseEmitter> emitters = sseEmitters.getEmitters();
        SseEmitter emitter = emitters.get(userId);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(userId)
                        .name("order")
                        .data(data));
            } catch (IOException exception) {
                throw new BusinessException(ExceptionCode.IO_EXCEPTION);
            }
        }
    }
}
