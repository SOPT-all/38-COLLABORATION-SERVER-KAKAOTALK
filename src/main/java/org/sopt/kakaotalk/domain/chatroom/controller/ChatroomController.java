package org.sopt.kakaotalk.domain.chatroom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.chatroom.code.ChatroomSuccessCode;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ChatroomListResponse;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ReadChatroomResponse;
import org.sopt.kakaotalk.domain.chatroom.service.ChatroomService;
import org.sopt.kakaotalk.global.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Chatroom", description = "채팅방 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatrooms")
public class ChatroomController {

  private final ChatroomService chatroomService;

  @Operation(
      summary = "채팅방 목록 조회",
      description =
          """
          채팅방 목록을 최근 메시지 시각 기준 내림차순으로 조회.
          folderName 단일 파라미터로 폴더별 필터 적용.
          """)
  @GetMapping
  public BaseResponse<ChatroomListResponse> getAllChatrooms(
      @Parameter(
              description =
                  """
                  폴더 이름 필터.

                  - 허용값: ALL, UNREAD, SOPT, SCHOOL, DI_TECH, GRADUATION
                  - ALL: 전체 채팅방 (미지정과 동일)
                  - UNREAD: 안 읽은 메시지가 있는 채팅방 (unreadCount > 0)
                  - SOPT / SCHOOL / DI_TECH / GRADUATION: 해당 폴더에 속한 채팅방
                  """,
              example = "SOPT")
          @RequestParam(required = false)
          String folderName) {
    ChatroomListResponse response = chatroomService.getAllChatrooms(folderName);
    return BaseResponse.success(ChatroomSuccessCode.CHATROOMS_FETCHED, response);
  }

  @Operation(summary = "채팅방 읽음 처리", description = "특정 채팅방을 읽음 처리. 해당 채팅방의 unreadCount를 0으로 갱신.")
  @PatchMapping("/{chatroomId}/read")
  public BaseResponse<ReadChatroomResponse> readChatroom(
      @Parameter(description = "읽음 처리할 채팅방의 고유 ID", example = "1") @PathVariable Long chatroomId) {
    ReadChatroomResponse response = chatroomService.readChatroom(chatroomId);
    return BaseResponse.success(ChatroomSuccessCode.READ_MARKED, response);
  }
}
