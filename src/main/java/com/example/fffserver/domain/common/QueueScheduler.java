package com.example.fffserver.domain.common;

import com.example.fffserver.domain.answer.domain.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduler.enable", havingValue = "true", matchIfMissing = true)
@Slf4j
public class QueueScheduler {

    private final SubmissionService submissionService;

    public QueueScheduler(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @Scheduled(fixedDelay = 1000)
    private void eventScheduler() {
        submissionService.enter();
        submissionService.getOrder();
    }
}
