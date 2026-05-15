package org.sopt.kakaotalk.domain.folder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.folder.code.FolderSuccessCode;
import org.sopt.kakaotalk.domain.folder.dto.response.FolderListResponse;
import org.sopt.kakaotalk.domain.folder.service.FolderService;
import org.sopt.kakaotalk.global.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Folder", description = "폴더 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/folders")
public class FolderController {

    private final FolderService folderService;

    @Operation(summary = "폴더 목록 조회", description = "폴더 관리 화면에서 사용할 폴더 목록을 조회합니다.")
    @GetMapping
    public BaseResponse<FolderListResponse> getFolders() {
        FolderListResponse response = folderService.getFolders();
        return BaseResponse.success(FolderSuccessCode.FOLDERS_FETCHED, response);
    }
}