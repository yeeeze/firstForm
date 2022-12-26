package com.example.fffserver.domain.form.domain.vo;

import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventFactory {

    private static final Map<ObjectId, Event> eventMapCache = new HashMap<>();

    public static Event createfromForm(Form form) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(form.getEndTime())) {
            throw new BusinessException(ExceptionCode.AFTER_END);
        }

        ObjectId formId = form.getId();
        if (eventMapCache.containsKey(formId)) {
            return eventMapCache.get(formId);
        }
        Event event = new Event(formId, form.getStartTime(), form.getEndTime(), form.getWinnersNumber());
        eventMapCache.put(formId, event);
        return event;
    }

    public Set<Map.Entry<ObjectId, Event>> events() {
        return Set.copyOf(eventMapCache.entrySet());
    }

    public void removeEvent(ObjectId formId) {
        eventMapCache.remove(formId);
    }
}
