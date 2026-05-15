package org.sopt.kakaotalk.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.kakaotalk.global.code.ErrorCode;
import org.sopt.kakaotalk.global.code.SuccessCode;

public record BaseResponse<T>(
    @Schema(description = "HTTP 상태 코드", example = "200") int status,
    @Schema(description = "서버 커스텀 응답 코드", example = "COM_200_001") String code,
    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.") String message,
    @Schema(description = "실제 응답 데이터") T data) {

  public static <T> BaseResponse<T> success(SuccessCode successCode, T data) {
    return new BaseResponse<>(
        successCode.getHttpStatus().value(), successCode.getCode(), successCode.getMessage(), data);
  }

  public static BaseResponse<Void> success(SuccessCode successCode) {
    return new BaseResponse<>(
        successCode.getHttpStatus().value(), successCode.getCode(), successCode.getMessage(), null);
  }

  public static BaseResponse<Void> failure(ErrorCode errorCode) {
    return new BaseResponse<>(
        errorCode.getHttpStatus().value(), errorCode.getCode(), errorCode.getMessage(), null);
  }

  // @Valid 실패 시 필드별 에러를 data에 담음
  public static <T> BaseResponse<T> failure(ErrorCode errorCode, T data) {
    return new BaseResponse<>(
        errorCode.getHttpStatus().value(), errorCode.getCode(), errorCode.getMessage(), data);
  }
}
