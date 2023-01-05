package com.example.fffserver.domain.answer.controller;

import com.example.fffserver.domain.answer.domain.EventStreamDataJson;
import com.example.fffserver.domain.answer.domain.SubmissionService;
import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
public class SseController {

    private static final Long SSE_DEFAULT_TIMEOUT = 60 * 1000L;

    private final SseEmitters sseEmitters;
    private final SubmissionService submissionService;

    public SseController(SseEmitters sseEmitters, SubmissionService submissionService) {
        this.sseEmitters = sseEmitters;
        this.submissionService = submissionService;
    }

    /**
     * sse 이벤트 구독
     */
    @GetMapping(value = "/api/v1/answer/{userId}/order", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@PathVariable String userId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Transfer-Encoding", "chunked");
        responseHeaders.set("X-Accel-Buffering", "no");

        SseEmitter emitter = new SseEmitter(SSE_DEFAULT_TIMEOUT);
        sseEmitters.add(userId, emitter);

        EventStreamDataJson lastEventData = sseEmitters.getEventByUserId(userId);
        if (lastEventData != null) {
            submissionService.resendLastEvent(userId, lastEventData);
        }

        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new BusinessException(ExceptionCode.IO_EXCEPTION);
        }
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(emitter);
    }
}
