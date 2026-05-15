package org.sopt.kakaotalk.domain.chatroom.dto.response;

import java.util.List;

public record ChatroomListResponse(List<ChatroomResponse> chatrooms) {

  public static ChatroomListResponse from(List<ChatroomResponse> chatrooms) {
    return new ChatroomListResponse(chatrooms);
  }
}
