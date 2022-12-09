package com.example.fffserver.domain.form.controller;

import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.dto.PostFormReq;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.application.QuestionService;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FormController {

    private final FormService formService;
    private final QuestionService questionService;

    public FormController(FormService formService, QuestionService questionService) {
        this.formService = formService;
        this.questionService = questionService;
    }

    @PostMapping("/api/v1/form")
    public ResponseEntity<String> createForm(@RequestBody PostFormReq postFormReq) {
        Form form = postFormReq.toForm();
        List<Question> questions = form.getQuestions();

        questionService.insertList(questions);
        String formId = formService.insert(form);

        return ResponseEntity.ok().body(formId);
    }

//    @GetMapping("/api/v1/form/{formId}")
}
