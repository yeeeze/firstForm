package com.example.fffserver.global.exception;

public enum ExceptionCode {

  BEFORE_START(404, "시작 시간을 확인해주세요."),
  AFTER_END(404, "이벤트가 마감되었습니다."),
  EMPTY_RESULT(404, "DB 조회 결과가 없습니다."),
  IO_EXCEPTION(500, "서버 연결 오류");

  int status;
  String message;

  ExceptionCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
