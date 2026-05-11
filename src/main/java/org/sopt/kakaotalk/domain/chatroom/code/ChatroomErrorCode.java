package org.sopt.kakaotalk.domain.chatroom.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.global.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatroomErrorCode implements ErrorCode {

    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHT_404_001", "존재하지 않는 채팅방입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}