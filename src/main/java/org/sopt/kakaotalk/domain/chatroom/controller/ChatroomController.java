package org.sopt.kakaotalk.domain.chatroom.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.chatroom.code.ChatroomSuccessCode;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ChatroomListResponse;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ReadChatroomResponse;
import org.sopt.kakaotalk.domain.chatroom.service.ChatroomService;
import org.sopt.kakaotalk.domain.folder.entity.FolderName;
import org.sopt.kakaotalk.global.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatrooms")
public class ChatroomController {

  private final ChatroomService chatroomService;

  @GetMapping
  public BaseResponse<ChatroomListResponse> getAllChatrooms(
      @RequestParam(required = false) FolderName folderName,
      @RequestParam(defaultValue = "false") boolean unreadOnly) {
    ChatroomListResponse response = chatroomService.getAllChatrooms(folderName, unreadOnly);
    return BaseResponse.success(ChatroomSuccessCode.CHATROOMS_FETCHED, response);
  }

  @PatchMapping("/{chatroomId}/read")
  public BaseResponse<ReadChatroomResponse> readChatroom(@PathVariable Long chatroomId) {
    ReadChatroomResponse response = chatroomService.readChatroom(chatroomId);
    return BaseResponse.success(ChatroomSuccessCode.READ_MARKED, response);
  }
}
