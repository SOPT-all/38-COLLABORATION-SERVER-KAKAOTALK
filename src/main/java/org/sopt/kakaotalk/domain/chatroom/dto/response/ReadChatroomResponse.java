package org.sopt.kakaotalk.domain.chatroom.dto.response;

import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;

public record ReadChatroomResponse(Long chatroomId, Integer unreadCount) {

  public static ReadChatroomResponse from(Chatroom chatroom) {
    return new ReadChatroomResponse(chatroom.getId(), chatroom.getUnreadCount());
  }
}
