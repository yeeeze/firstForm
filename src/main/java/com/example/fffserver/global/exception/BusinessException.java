package com.example.fffserver.global.exception;

public class BusinessException extends RuntimeException {

    private final int status;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessege());
        this.status = exceptionCode.getStatus();
    }

    public int getStatus() {
        return status;
    }
}
