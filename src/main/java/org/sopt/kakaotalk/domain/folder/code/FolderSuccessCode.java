package org.sopt.kakaotalk.domain.folder.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.global.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FolderSuccessCode implements SuccessCode {
    FOLDERS_FETCHED(HttpStatus.OK, "FLD_200_001", "폴더 목록 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}