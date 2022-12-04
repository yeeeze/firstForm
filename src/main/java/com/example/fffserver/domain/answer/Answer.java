package com.example.fffserver.domain.answer;

import com.example.fffserver.domain.common.BaseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("answer")
public class Answer extends BaseEntity {

    @Id
    private ObjectId id;
    private String content;
    private ObjectId formId;
    private ObjectId questionId;

    public Answer() {
    }
}
