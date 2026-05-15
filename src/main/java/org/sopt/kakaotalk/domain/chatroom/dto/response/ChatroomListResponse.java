package org.sopt.kakaotalk.domain.chatroom.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "채팅방 목록 조회 응답")
public record ChatroomListResponse(
    @Schema(description = "채팅방 목록") List<ChatroomResponse> chatrooms) {

  public static ChatroomListResponse from(List<ChatroomResponse> chatrooms) {
    return new ChatroomListResponse(chatrooms);
  }
}
