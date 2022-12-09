package com.example.fffserver.domain.form.dto;

import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.domain.entity.Question;
import com.example.fffserver.domain.question.dto.QuestionPostReq;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostFormReq {

    private final String title;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Integer winnersNumber;
    private final List<QuestionPostReq> questionPostReqs;      // title, type

    public PostFormReq(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Integer winnersNumber, List<QuestionPostReq> questionPostReqs) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.winnersNumber = winnersNumber;
        this.questionPostReqs = questionPostReqs;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getWinnersNumber() {
        return winnersNumber;
    }

    public Form toForm() {
        List<Question> questions = questionPostReqs.stream()
                .map(questionPostReq -> new Question(questionPostReq.getTitle(), questionPostReq.getType()))
                .collect(Collectors.toList());
        return new Form(startTime, endTime, winnersNumber, questions);
    }
}
