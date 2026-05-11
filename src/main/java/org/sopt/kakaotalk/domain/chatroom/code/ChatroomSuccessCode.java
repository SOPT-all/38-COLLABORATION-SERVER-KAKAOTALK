package org.sopt.kakaotalk.domain.chatroom.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.global.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatroomSuccessCode implements SuccessCode {
    CHATROOMS_FETCHED(HttpStatus.OK, "CHT_200_001", "채팅방 목록 조회에 성공했습니다."),
    READ_MARKED(HttpStatus.OK, "CHT_200_002", "채팅방 읽음 처리가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
