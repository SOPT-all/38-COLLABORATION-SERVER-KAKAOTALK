package org.sopt.kakaotalk.domain.chatroom.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;

@Schema(description = "채팅방 읽음 처리 응답")
public record ReadChatroomResponse(
    @Schema(description = "읽음 처리된 채팅방의 고유 ID", example = "1") Long chatroomId,
    @Schema(description = "갱신된 안 읽은 메시지 수. 항상 0", example = "0") Integer unreadCount) {

  public static ReadChatroomResponse from(Chatroom chatroom) {
    return new ReadChatroomResponse(chatroom.getId(), chatroom.getUnreadCount());
  }
}
