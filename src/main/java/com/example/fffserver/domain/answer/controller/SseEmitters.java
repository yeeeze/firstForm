package com.example.fffserver.domain.answer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SseEmitters {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, String> lastEvents = new ConcurrentHashMap<>();

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

    public void setLastEvent(String id, String eventData) {
        lastEvents.put(id, eventData);
    }

    public String getEventByUserId(String userId) {
        return Optional.ofNullable(lastEvents.get(userId)).orElse("");
    }
}
