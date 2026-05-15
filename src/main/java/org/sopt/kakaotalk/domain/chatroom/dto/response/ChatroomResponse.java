package org.sopt.kakaotalk.domain.chatroom.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;

@Schema(description = "채팅방 단건 응답")
public record ChatroomResponse(
    @Schema(description = "채팅방 고유 ID", example = "1") Long chatroomId,
    @Schema(description = "채팅방 제목", example = "[LET'S SOPT] 전체 공지방") String title,
    @Schema(description = "참여자 수", example = "153") Integer participantCount,
    @Schema(description = "마지막 메시지 텍스트", example = "💫 LET'S SOPT 6차 세미나 공지 💫") String lastMessage,
    @Schema(description = "마지막 메시지 시각 (ISO 8601)", example = "2026-05-12T14:30:00")
        LocalDateTime lastMessageAt,
    @Schema(description = "안 읽은 메시지 수 (0 이상)", example = "2") Integer unreadCount,
    @Schema(description = "이 채팅방이 속한 폴더 이름 배열. 어디에도 속하지 않으면 빈 배열", example = "[\"SOPT\"]")
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
