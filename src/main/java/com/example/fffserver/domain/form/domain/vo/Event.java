package com.example.fffserver.domain.form.domain.vo;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Getter
@Slf4j
public class Event {

  private static final int END_COUNT = 0;

  private final ObjectId formId;
  private final LocalDateTime start;
  private final LocalDateTime end;
  private final int limitCount;
  private int winnersNum;

  Event(ObjectId formId, LocalDateTime start, LocalDateTime end, int winnersNum) {
    this.formId = formId;
    this.start = start;
    this.end = end;
    this.winnersNum = winnersNum;
    this.limitCount = (int) (winnersNum * 0.1);
  }

  public synchronized void decrease() {
    this.winnersNum--;
    log.info("{} 남은 갯수 : {}", this.formId.toString(), this.winnersNum);
  }

  public boolean isEnd() {
    return this.winnersNum == END_COUNT;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Event)) {
      return false;
    }
    Event event = (Event) o;
    return getFormId().equals(event.getFormId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFormId());
  }
}
