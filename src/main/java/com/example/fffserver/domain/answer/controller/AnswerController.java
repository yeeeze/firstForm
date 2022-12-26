package com.example.fffserver.domain.answer.controller;

import com.example.fffserver.domain.answer.domain.SubmissionService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.answer.dto.PostAnswerListRequest;
import com.example.fffserver.domain.answer.dto.PostAnswerResponse;
import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.vo.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AnswerController {

    // TODO : 퍼사드 패턴 - 2개의 서비스를 의존받을 때 (refactor)
    private final SubmissionService submissionService;
    private final FormService formService;

    public AnswerController(SubmissionService submissionService, FormService formService) {
        this.submissionService = submissionService;
        this.formService = formService;
    }

    /**
     * 선착순 응답 준비, userId 발급 (시작시간 이후부터 가능)
     */
    @GetMapping("/api/v1/answer/userId")
    public ResponseEntity<String> getUserId() {
        final String userId = String.valueOf(UUID.randomUUID());
        return ResponseEntity.ok().body(userId);
    }

    /**
     * 응답 전송 선착순 참여
     */
    @PostMapping("/api/v1/answer/{formId}")
    public ResponseEntity<PostAnswerResponse> postAnswer(@PathVariable String formId, @RequestBody PostAnswerListRequest postAnswerListRequest) {
        List<Answer> answerList = postAnswerListRequest.toAnswerListMappedForm(formId);
        Event event = formService.getEvent(formId);

        Long waitCount = submissionService.addQueue(postAnswerListRequest.getUserId(), event, answerList);
        PostAnswerResponse waittingDto = PostAnswerResponse.createWaitting(waitCount, postAnswerListRequest.getUserId());
        return ResponseEntity.ok().body(waittingDto);
    }
}
