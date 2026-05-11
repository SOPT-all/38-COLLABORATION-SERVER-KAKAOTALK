package org.sopt.kakaotalk.global.code;

import org.springframework.http.HttpStatus;

public interface ApiCode {
  HttpStatus getHttpStatus();

  String getCode();

  String getMessage();
}
