package com.example.fffserver.domain.question.dto;

import com.example.fffserver.domain.question.domain.entity.Question;

import java.util.List;

public class QuestionPostReq {
    private final String title;
    private final String type;

    private final List<String> checkboxList;

    public QuestionPostReq(String title, String type, List<String> checkboxList) {
        this.title = title;
        this.type = type;
        this.checkboxList = checkboxList;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public List<String> getCheckboxList() {
        return checkboxList;
    }

    public Question toQuestion() {
        return new Question(title, type, checkboxList);
    }
}
