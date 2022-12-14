package com.example.fffserver.domain.answer.dto;

public class PostAnswerResponse {

    private final String userName;
    private Long count;    // 대기번호

    private PostAnswerResponse(String userName) {
        this.userName = userName;
    }

    private PostAnswerResponse(Long count, String userName) {
        this.count = count;
        this.userName = userName;
    }

    public static PostAnswerResponse createWaitting(Long count, String userName) {
        return new PostAnswerResponse(count, userName);
    }
}
