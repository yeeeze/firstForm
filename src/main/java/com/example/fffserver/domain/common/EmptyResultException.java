package com.example.fffserver.domain.common;

import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;

public class EmptyResultException extends BusinessException {

    public EmptyResultException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
