package com.example.fffserver.domain.answer.domain;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.form.domain.vo.Event;
import com.example.fffserver.domain.form.domain.vo.EventFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SubmissionService {

    // TODO : 스케줄러 API 응답 구현
    // TODO : 현재 진행되고 있는 event가 다수일 경우 getOrder()등을 수행할 때 뒷순서인 event는 앞에 event가 끝날 때까지 기다려야 되는건가? (병렬처리 필요)
    // TODO : 유저들의 정보인 List<Answer>을 자바 메모리 상에 저장하지 않고 외부의 메세지큐에 저장하기 (refactor)

    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = -1;
    // key -> formId(event) + userName(threadName)
    private final Map<String, List<Answer>> userAnswerMap = new ConcurrentHashMap<>();

    private final EventFactory eventFactory;
    private final AnswerService answerService;
    private final RedisTemplate<String, String> redisTemplate;

    public SubmissionService(EventFactory eventFactory, AnswerService answerService, RedisTemplate<String, String> redisTemplate) {
        this.eventFactory = eventFactory;
        this.answerService = answerService;
        this.redisTemplate = redisTemplate;
    }

    // 대기열, 이벤트 추가
    public Long addQueue(String userName, Event event, List<Answer> answerList) {
        final long now = System.currentTimeMillis();

        redisTemplate.opsForZSet().add(event.getFormId().toString(), userName, now);
        userAnswerMap.put(event.getFormId().toString() + userName, answerList);
        log.info("이벤트명: {}, 대기열 및 응답 map 추가 완료 : {} {} ({}초)", event.getFormId().toString(), userName, Thread.currentThread().getName(), now);

        return redisTemplate.opsForZSet().rank(event.getFormId().toString(), userName);
    }


    // 대기열 순번 안내
    public void getOrder() {
        eventFactory.events().parallelStream().forEach(objectIdEventEntry -> {
            String eventId = objectIdEventEntry.getValue().getFormId().toString();

            Set<String> waitingQueue = redisTemplate.opsForZSet().range(eventId, FIRST_INDEX, LAST_INDEX);
            if (waitingQueue == null) {
                return;
            }
            for (String userName : waitingQueue) {
                Long rank = redisTemplate.opsForZSet().rank(eventId, userName);
                log.info("{} 이벤트의 {}님의 현재 대기번호는 {}입니다.",eventId, userName, rank);
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
                    for (String userName : waitingQueue) {
                        if (isEnd(event)) {
                            eventFactory.removeEvent(event.getFormId());
//                          redisTemplate.opsForZSet().removeRange(event.getFormId().toString(), FIRST_INDEX, LAST_INDEX);
                            return;
                        }
                        event.decrease();
                        redisTemplate.opsForZSet().remove(eventId, userName);
                        List<Answer> answerList = userAnswerMap.get(eventId + userName);
                        answerService.mappedQuestionAndInsert(answerList);
                        log.info("{}님의 응답 제출이 완료됐습니다. 이벤트: {}, 응답: {}", userName, eventId, answerList);
                    }
                });
    }

    // 선착순 종료 체크
    private boolean isEnd(Event event) {
        if (event.isEnd()) {
            log.info("==== {}의 선착순이 마감되었습니다. ====", event.getFormId().toString());
            return true;
        }

        return false;
    }

    // 남아있는 대기열 유저 숫자 확인
    public Long getWaitingQueueSize(Event event) {
        return redisTemplate.opsForZSet().size(event.getFormId().toString());
    }
}
