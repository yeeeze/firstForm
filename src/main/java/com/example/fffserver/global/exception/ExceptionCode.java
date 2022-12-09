package com.example.fffserver.global.exception;

public enum ExceptionCode {

    EMPTY_RESULT(404, "DB 조회 결과가 없습니다.");

    int status;
    String messege;

    ExceptionCode(int status, String messege) {
        this.status = status;
        this.messege = messege;
    }

    public int getStatus() {
        return status;
    }

    public String getMessege() {
        return messege;
    }
}
