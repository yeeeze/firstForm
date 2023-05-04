package com.example.fffserver.domain.answer.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@Slf4j
public class SseEmitters {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, EventStreamDataJson> lastEvents = new ConcurrentHashMap<>();

    public SseEmitter add(String userId, SseEmitter emitter) {
        emitters.put(userId, emitter);
        log.info("new emitter added: {}", userId);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitters.remove(userId);
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });
        return emitter;
    }

    public Map<String, SseEmitter> getEmitters() {
        return this.emitters;
    }

    public void setLastEvent(String id, EventStreamDataJson eventDataJson) {
        lastEvents.put(id, eventDataJson);
    }

    public EventStreamDataJson getEventByUserId(String userId) {
        return lastEvents.get(userId);
    }
}
