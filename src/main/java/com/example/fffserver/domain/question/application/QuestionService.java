package com.example.fffserver.domain.question.application;

import com.example.fffserver.domain.question.domain.QuestionReposioty;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionReposioty questionReposioty;

    public QuestionService(QuestionReposioty questionReposioty) {
        this.questionReposioty = questionReposioty;
    }

    public void insertList(List<Question> questions) {
        questionReposioty.saveAll(questions);
    }
}
