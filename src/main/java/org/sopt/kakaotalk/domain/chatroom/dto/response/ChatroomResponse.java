package org.sopt.kakaotalk.domain.chatroom.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;

public record ChatroomResponse(
    Long chatroomId,
    String title,
    Integer participantCount,
    String lastMessage,
    LocalDateTime lastMessageAt,
    Integer unreadCount,
    List<String> folderNames) {

  public static ChatroomResponse from(Chatroom chatroom, List<String> folderNames) {
    return new ChatroomResponse(
        chatroom.getId(),
        chatroom.getTitle(),
        chatroom.getParticipantCount(),
        chatroom.getLastMessage(),
        chatroom.getLastMessageAt(),
        chatroom.getUnreadCount(),
        folderNames);
  }
}
