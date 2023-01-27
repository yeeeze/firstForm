package com.example.fffserver.domain.common;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
public abstract class BaseEntity {

  @CreatedDate
  private LocalDateTime createdAt;
}