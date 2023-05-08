package com.example.fffserver.domain.answer.domain.messagingQueue;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// TODO: RabbitMQ -> 카프카로 변경
@Component
@Slf4j
public class Consumer {

  private AnswerService answerService;

  public Consumer(AnswerService answerService) {
    this.answerService = answerService;
  }

  @RabbitListener(queues = "answer.queue")
  public void consume(Message message) throws JsonProcessingException {
    String messageString = new String(message.getBody(), StandardCharsets.UTF_8);

    ObjectMapper objectMapper = new ObjectMapper();
    AnswerQueueListRequestDto answerQueueListRequestDto = objectMapper.readValue(messageString,
        AnswerQueueListRequestDto.class);

    log.info("answerId: {}", answerQueueListRequestDto.getAnswerList().get(0).getAnswerId());
    log.info("userId: {}", answerQueueListRequestDto.getAnswerList().get(0).getUserId());
    log.info("content: {}", answerQueueListRequestDto.getAnswerList().get(0).getContent());

    // TODO: DB 삽입
  }

  // DB insert
  public void insert(List<Answer> answerList) {
    answerService.mappedQuestionAndInsert(answerList);
  }
}