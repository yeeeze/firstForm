package com.example.fffserver.domain.answer.dto;

import com.example.fffserver.domain.answer.domain.entity.Answer;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class PostAnswerListRequest {

    private List<String> content;

    public PostAnswerListRequest() {
    }

    public PostAnswerListRequest(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<Answer> toAnswerList(String formId) {
        return content.stream()
                .map(c -> new Answer(c, new ObjectId(formId)))
                .collect(Collectors.toList());
    }
}
