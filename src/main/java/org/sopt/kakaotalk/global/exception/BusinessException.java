package org.sopt.kakaotalk.global.exception;

import lombok.Getter;
import org.sopt.kakaotalk.global.code.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    // 부모 클래스(RuntimeException)에 메시지 전달 -> e.getMessage()와 같이 사용 가능
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
