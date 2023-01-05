package com.example.fffserver.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;
}
