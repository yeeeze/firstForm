package com.example.fffserver.domain.answer;

import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;

public class EmptyResultException extends BusinessException {

    public EmptyResultException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
