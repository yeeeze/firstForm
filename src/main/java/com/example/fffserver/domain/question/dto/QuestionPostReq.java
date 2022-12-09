package com.example.fffserver.domain.question.dto;

public class QuestionPostReq {
    private final String title;
    private final String type;

    public QuestionPostReq(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
