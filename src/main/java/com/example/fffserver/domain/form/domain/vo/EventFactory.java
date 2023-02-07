package com.example.fffserver.domain.form.domain.vo;

import com.example.fffserver.domain.form.domain.entity.Form;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class EventFactory {

  private static final Map<ObjectId, Event> eventMapCache = new HashMap<>();

  public static Event createfromForm(Form form) {
    ObjectId formId = form.getId();
    if (eventMapCache.containsKey(formId)) {
      return eventMapCache.get(formId);
    }
    Event event = new Event(formId, form.getStartTime(), form.getEndTime(),
        form.getWinnersNumber());
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
