package com.example.fffserver.domain.answer.domain.entity;

import com.example.fffserver.domain.common.BaseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("answer")
public class Answer extends BaseEntity {

    @Id
    private ObjectId id;
    private String userId;
    private String content;
    private ObjectId formId;
    private ObjectId questionId;

    public Answer() {
    }

    public Answer(String userId, String content, ObjectId formId) {
        this.userId = userId;
        this.content = content;
        this.formId = formId;
    }

    public void mappedQuestionId(ObjectId questionId) {
        this.questionId = questionId;
    }

    public ObjectId getFormId() {
        return formId;
    }
}
