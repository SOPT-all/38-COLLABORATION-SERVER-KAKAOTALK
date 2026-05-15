package org.sopt.kakaotalk.domain.folder.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.sopt.kakaotalk.domain.folder.entity.Folder;

@Schema(description = "폴더 목록 조회 응답")
public record FolderListResponse(
        @Schema(description = "폴더 목록")
        List<FolderResponse> folders
) {

    public static FolderListResponse from(List<Folder> folders) {
        return new FolderListResponse(
                folders.stream()
                        .map(FolderResponse::from)
                        .toList()
        );
    }

    @Schema(description = "폴더 단건 응답")
    public record FolderResponse(
            @Schema(description = "폴더 고유 ID", example = "1")
            Long folderId,

            @Schema(description = "폴더 이름 enum 값", example = "SOPT")
            String name,

            @Schema(description = "폴더 아이콘 enum 값", example = "ICON_RYAN")
            String icon
    ) {

        public static FolderResponse from(Folder folder) {
            return new FolderResponse(
                    folder.getFolderId(),
                    folder.getFolderName().name(),
                    folder.getFolderIcon().name()
            );
        }
    }
}