package com.example.fffserver.domain.answer.dto;

import com.example.fffserver.domain.answer.domain.entity.Answer;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostAnswerListRequest {

    private String userId;
    private List<String> content;

    public PostAnswerListRequest(String userId, List<String> content) {
        this.userId = userId;
        this.content = content;
    }

    public List<Answer> toAnswerListMappedForm(String formId) {
        return content.stream()
                .map(c -> new Answer(this.userId, c, new ObjectId(formId)))
                .collect(Collectors.toList());
    }
}
