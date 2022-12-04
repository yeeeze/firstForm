package com.example.fffserver;

import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FormServiceTest {

    @Autowired
    FormService formService;

    @AfterEach
    void afterRemove() {
        formService.deleteAll();
    }

    @Test
    @DisplayName("insert 성공")
    void insertForm() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("학번", "TEXT"));
        questions.add(new Question("이름", "TEXT"));
        Form form = new Form(LocalDateTime.now(), LocalDateTime.of(2022, Month.DECEMBER, 7, 23, 59), 20, questions);

        formService.insert(form);

        List<Form> all = formService.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getId()).isEqualTo(form.getId());
    }
}
