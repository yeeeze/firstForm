package com.example.fffserver.domain.answer.controller;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.domain.SubmissionService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.answer.dto.PostAnswerListRequest;
import com.example.fffserver.domain.answer.dto.PostAnswerResponse;
import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.vo.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {

    private final AnswerService answerService;

    // TODO : 퍼사드 패턴 - 2개의 서비스를 의존받을 때 (refactor)
    private final SubmissionService submissionService;
    private final FormService formService;

    public AnswerController(AnswerService answerService, SubmissionService submissionService, FormService formService) {
        this.answerService = answerService;
        this.submissionService = submissionService;
        this.formService = formService;
    }

    @PostMapping("/api/v1/answer/{formId}")
    public ResponseEntity<PostAnswerResponse> postAnswer(@PathVariable String formId, @RequestBody PostAnswerListRequest postAnswerListRequest) {
        List<Answer> answerList = postAnswerListRequest.toAnswerListMappedForm(formId);
        Event event = formService.getEvent(formId);
        final String userName = Thread.currentThread().getName();

        Long waitCount = submissionService.addQueue(userName, event, answerList);

        return ResponseEntity.ok().body(PostAnswerResponse.createWaitting(waitCount, userName));
    }
}
