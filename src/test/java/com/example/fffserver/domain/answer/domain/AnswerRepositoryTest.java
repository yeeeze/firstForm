package com.example.fffserver.domain.answer.domain;

import com.example.fffserver.domain.answer.domain.entity.Answer;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @AfterEach
    void tearDown() {
        answerRepository.deleteAll();
    }

    @Test
    @DisplayName("findAllByFormId 성공 테스트")
    void findByFormId() {
        ObjectId objectId = new ObjectId();
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Answer answer = new Answer(String.valueOf(i), objectId);
            answerList.add(answer);
        }
        answerRepository.saveAll(answerList);

        ObjectId objectId2 = new ObjectId();
        Answer answer = new Answer("test", objectId2);
        answerRepository.save(answer);

        List<Answer> allByFormId = answerRepository.findAllByFormId(objectId);
        List<Answer> allByFormId2 = answerRepository.findAllByFormId(objectId2);

        assertThat(allByFormId).hasSize(3);
        assertThat(allByFormId2).hasSize(1);
    }
}
