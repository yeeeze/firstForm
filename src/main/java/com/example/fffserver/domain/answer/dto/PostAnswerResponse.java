package com.example.fffserver.domain.answer.dto;

import lombok.Getter;

@Getter
public class PostAnswerResponse {

    private String userId;
    private Long count;    // 대기번호

    public PostAnswerResponse() {
    }

    private PostAnswerResponse(String userId) {
        this.userId = userId;
    }

    private PostAnswerResponse(Long count, String userId) {
        this.count = count;
        this.userId = userId;
    }

    public static PostAnswerResponse createWaitting(Long count, String userId) {
        return new PostAnswerResponse(count, userId);
    }
}
