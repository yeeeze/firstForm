package com.example.fffserver.domain.answer.controller;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.answer.dto.PostAnswerListRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/api/v1/answer/{formId}")
    public ResponseEntity<HttpStatus> postAnswer(@PathVariable String formId, @RequestBody PostAnswerListRequest postAnswerListRequest) {
        List<Answer> answerList = postAnswerListRequest.toAnswerList(formId);
        answerService.insert(answerList);
        return ResponseEntity.ok().build();
    }
}
