package org.sopt.kakaotalk.domain.folder.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.kakaotalk.domain.folder.entity.Folder;

@Schema(description = "폴더 단건 응답")
public record FolderResponse(
    @Schema(description = "폴더 고유 ID 또는 동적 폴더 ID", example = "1") Long folderId,
    @Schema(description = "폴더 이름 또는 동적 필터 이름", example = "SOPT") String name,
    @Schema(description = "폴더 아이콘 enum 값", example = "ICON_RYAN") String icon) {

  private static final long ALL_FOLDER_ID = -1L;
  private static final long UNREAD_FOLDER_ID = -2L;
  private static final String ALL_FOLDER_NAME = "ALL";
  private static final String UNREAD_FOLDER_NAME = "UNREAD";
  private static final String ALL_FOLDER_ICON = "ICON_NONE";
  private static final String UNREAD_FOLDER_ICON = "ICON_SPEECHBUBBLE";

  public static FolderResponse all() {
    return new FolderResponse(ALL_FOLDER_ID, ALL_FOLDER_NAME, ALL_FOLDER_ICON);
  }

  public static FolderResponse unread() {
    return new FolderResponse(UNREAD_FOLDER_ID, UNREAD_FOLDER_NAME, UNREAD_FOLDER_ICON);
  }

  public static FolderResponse from(Folder folder) {
    return new FolderResponse(
        folder.getFolderId(), folder.getFolderName().name(), folder.getFolderIcon().name());
  }
}
