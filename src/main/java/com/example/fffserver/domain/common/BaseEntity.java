package com.example.fffserver.domain.common;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;
}