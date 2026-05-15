package org.sopt.kakaotalk.domain.folder.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.sopt.kakaotalk.domain.folder.entity.Folder;

@Schema(description = "폴더 목록 조회 응답")
public record FolderListResponse(@Schema(description = "폴더 목록") List<FolderResponse> folders) {

  public static FolderListResponse from(List<Folder> folders) {
    List<FolderResponse> responses = new ArrayList<>();
    responses.add(FolderResponse.all());
    responses.add(FolderResponse.unread());
    responses.addAll(folders.stream().map(FolderResponse::from).toList());

    return new FolderListResponse(responses);
  }
}
