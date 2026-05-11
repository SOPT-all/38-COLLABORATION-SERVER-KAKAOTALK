package org.sopt.kakaotalk.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

  // 400 - 클라이언트 요청 형식이 잘못된 경우
  INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COM_400_001", "잘못된 요청입니다."),

  // 500 - 예상하지 못한 모든 예외의 fallback
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM_500_001", "서버 내부 오류가 발생했습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;
}
