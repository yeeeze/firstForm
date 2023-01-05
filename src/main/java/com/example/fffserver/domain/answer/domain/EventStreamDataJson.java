package com.example.fffserver.domain.answer.domain;

import lombok.Getter;

@Getter
public class EventStreamDataJson {

    private final String msg;
    private final EventStatus status;

    protected EventStreamDataJson(String msg, EventStatus status) {
        this.msg = msg;
        this.status = status;
    }
}
