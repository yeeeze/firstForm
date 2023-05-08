package com.example.fffserver.domain.answer.domain.messagingQueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  private final RabbitTemplate rabbitTemplate;

  public Producer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(AnswerQueueListRequestDto message) {
    rabbitTemplate.convertAndSend("answer.exchange", "answer.key", message);
  }
}
