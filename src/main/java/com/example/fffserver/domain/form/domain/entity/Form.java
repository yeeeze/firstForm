package com.example.fffserver.domain.form.domain.entity;

import com.example.fffserver.domain.common.BaseEntity;
import com.example.fffserver.domain.form.domain.StatusType;
import com.example.fffserver.domain.question.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Document("form")
public class Form extends BaseEntity {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer winnersNumber;

    private List<Question> questions;

    private String url;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    public Form() {
    }

    public Form(LocalDateTime startTime, LocalDateTime endTime, Integer winnersNumber, List<Question> questions) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.winnersNumber = winnersNumber;
        this.questions = questions;
        this.status = StatusType.ACTIVE;
    }

    public Form(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Integer winnersNumber, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.winnersNumber = winnersNumber;
        this.questions = questions;
        this.status = StatusType.ACTIVE;
    }

    public ObjectId getId() {
        return id;
    }
}
