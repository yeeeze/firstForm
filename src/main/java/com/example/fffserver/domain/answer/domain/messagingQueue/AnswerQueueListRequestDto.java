package com.example.fffserver.domain.answer.domain.messagingQueue;

import java.util.List;
import lombok.Getter;

@Getter
public class AnswerQueueListRequestDto {

  private List<AnswerQueueRequestDto> answerList;

  // 필드 1개인 경우 Jackson 이슈
  public AnswerQueueListRequestDto() {
  }

  public AnswerQueueListRequestDto(List<AnswerQueueRequestDto> answerList) {
    this.answerList = answerList;
  }
}
