package com.example.fffserver.domain.question;

import com.example.fffserver.domain.common.BaseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("question")
public class Question extends BaseEntity {

    @Id
    private ObjectId id;
    private String title;
    private QuestionType Type;

    public Question() {
    }

    public Question(String title, String type) {
        this.title = title;
        Type = QuestionType.valueOf(type);
    }
}
