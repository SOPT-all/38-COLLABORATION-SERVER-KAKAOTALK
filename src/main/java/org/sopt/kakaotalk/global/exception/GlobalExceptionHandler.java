package org.sopt.kakaotalk.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.sopt.kakaotalk.global.code.ErrorCode;
import org.sopt.kakaotalk.global.code.GlobalErrorCode;
import org.sopt.kakaotalk.global.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// Lombok의 log변수를 클래스에 자동 주입 -> log.warn(...) 등 호출을 별도 선언 없이 사용 가능
@Slf4j
// GlobalExceptionHandler 클래스가 모든 컨트롤러의 예외를 가로채는 전역 advice임을 Spring에게 알림
@RestControllerAdvice
public class GlobalExceptionHandler {

  // 1. 비즈니스 예외 - service에서 직접 던지는 도메인 예외
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException e) {
    ErrorCode errorCode = e.getErrorCode();
    log.warn("[BusinessException] {} - {}", errorCode.getCode(), e.getMessage());
    return ResponseEntity.status(errorCode.getHttpStatus()).body(BaseResponse.failure(errorCode));
  }

  // 2. @Valid Request DTO 검증 실패
  // 클라이언트에서 에러 응답을 통해 어느 입력 필드에서 어떤 @Valid 검증에 실패했는지 알 수 있도록 Map<String, String> 적용
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseResponse<Map<String, String>>> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e) {
    Map<String, String> fieldErrors = new HashMap<>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      fieldErrors.put(
          fieldError.getField(),
          Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Invalid value"));
    }
    log.warn("[MethodArgumentNotValid] {}", fieldErrors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST, fieldErrors));
  }

  // 3. 잘못된 JSON 본문
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseResponse<Void>> handleHttpMessageNotReadable(
      HttpMessageNotReadableException e) {
    log.warn("[HttpMessageNotReadable] {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST));
  }

  // 4. @PathVariable / @RequestParam 검증 실패 (Spring Boot 3.2+)
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<BaseResponse<Void>> handleHandlerMethodValidation(
      HandlerMethodValidationException e) {
    log.warn("[HandlerMethodValidation] {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST));
  }

  // 5. @PathVariable / @RequestParam 타입 변환 실패 (예: Long 자리에 문자열, enum에 미허용 값)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<BaseResponse<Void>> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException e) {
    log.warn("[MethodArgumentTypeMismatch] {} - {}", e.getName(), e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.failure(GlobalErrorCode.INVALID_REQUEST));
  }

  // 6. fallback — 위 5개에 안 잡힌 모든 예외
  // 실제로 디버깅이 필요한 케이스 -> Sentry/AlertManager 등에서 error 감지할 수 있도록 log.error() 적용
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<Void>> handleException(
      Exception e, HttpServletRequest request) {
    log.error(
        "[UnhandledException] uri={}, message={}", request.getRequestURI(), e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(BaseResponse.failure(GlobalErrorCode.INTERNAL_SERVER_ERROR));
  }
}
