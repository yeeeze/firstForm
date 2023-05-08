package com.example.fffserver.domain.answer.domain.messagingQueue;

import lombok.Getter;

@Getter
public class AnswerQueueRequestDto {

  private String answerId;
  private String userId;
  private String content;
  private String formId;
  private String questionId;

  public AnswerQueueRequestDto() {
  }

  public AnswerQueueRequestDto(String answerId, String userId, String content, String formId,
      String questionId) {
    this.answerId = answerId;
    this.userId = userId;
    this.content = content;
    this.formId = formId;
    this.questionId = questionId;
  }
}
