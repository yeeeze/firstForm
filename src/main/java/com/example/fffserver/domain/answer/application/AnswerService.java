package com.example.fffserver.domain.answer.application;

import com.example.fffserver.domain.answer.EmptyResultException;
import com.example.fffserver.domain.answer.domain.AnswerRepository;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.form.domain.FormRepository;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.fffserver.global.exception.ExceptionCode.EMPTY_RESULT;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final FormRepository formRepository;

    public AnswerService(AnswerRepository answerRepository, FormRepository formRepository) {
        this.answerRepository = answerRepository;
        this.formRepository = formRepository;
    }

    public void insert(List<Answer> answerList) {
        Form form = formRepository.findById(answerList.get(0).getFormId())
                .orElseThrow(() -> new EmptyResultException(EMPTY_RESULT));
        List<Question> questions = form.getQuestions();

        // questionId 맵핑
        for (int i = 0; i < answerList.size(); i++) {
            answerList.get(i).setQuestionId(questions.get(i).getId());
        }
        answerRepository.saveAll(answerList);
    }
}
