package com.example.fffserver;

import com.example.fffserver.domain.form.domain.FormRepository;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("MongoDB CRUD test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MongoTest {

    @Autowired
    FormRepository formRepository;

    ObjectId formId;

    @Order(1)
    @Test
    @DisplayName("insert 성공, 영속성 컨텍스트 관리되지 않음 확인")
    void insertForm() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("학번", "TEXT", null));
        questions.add(new Question("이름", "TEXT", null));
        Form form = new Form(LocalDateTime.now(), 20, questions);

        Form save = formRepository.save(form);
        formId = save.getId();
        Form find = formRepository.findById(save.getId()).get();

        assertThat(find.getId()).isEqualTo(save.getId());
        assertThat(find).isNotEqualTo(save);
    }

    @Order(2)
    @Test
    void tearDown() {
        formRepository.deleteById(formId);
    }
}
