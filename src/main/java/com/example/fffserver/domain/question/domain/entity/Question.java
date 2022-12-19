package com.example.fffserver.domain.question.domain.entity;

import com.example.fffserver.domain.common.BaseEntity;
import com.example.fffserver.domain.question.QuestionType;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Document("question")
public class Question extends BaseEntity {

    @Id
    private ObjectId id;
    private String title;
    private QuestionType type;

    private List<String> checkboxList;

    public Question() {
    }

    public Question(String title, String type, List<String> checkboxList) {
        this.title = title;
        this.type = QuestionType.valueOf(type);
        this.checkboxList = checkboxList;
    }
}
