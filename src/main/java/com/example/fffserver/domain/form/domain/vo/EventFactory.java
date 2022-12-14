package com.example.fffserver.domain.form.domain.vo;

import com.example.fffserver.domain.form.domain.entity.Form;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Component
public class EventFactory {

    private static final Map<ObjectId, Event> eventMapCache = new ConcurrentHashMap<>();

    public static Event createfromForm(Form form) {
        ObjectId formId = form.getId();
        if (eventMapCache.containsKey(formId)) {
            return eventMapCache.get(formId);
        }
        Event event = new Event(formId, form.getStartTime(), form.getEndTime(), form.getWinnersNumber());
        eventMapCache.put(formId, event);
        return event;
    }

    public Stream<Map.Entry<ObjectId, Event>> events() {
        return eventMapCache.entrySet().parallelStream();
    }

    public void removeEvent(ObjectId formId) {
        eventMapCache.remove(formId);
    }
}
