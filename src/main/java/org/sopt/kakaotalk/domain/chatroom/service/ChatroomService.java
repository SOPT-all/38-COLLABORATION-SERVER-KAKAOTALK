package org.sopt.kakaotalk.domain.chatroom.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.chatroom.code.ChatroomErrorCode;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ChatroomListResponse;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ChatroomResponse;
import org.sopt.kakaotalk.domain.chatroom.dto.response.ReadChatroomResponse;
import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomFolderRepository;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomRepository;
import org.sopt.kakaotalk.domain.folder.entity.FolderName;
import org.sopt.kakaotalk.global.code.GlobalErrorCode;
import org.sopt.kakaotalk.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatroomService {

  private static final String ALL = "ALL";
  private static final String UNREAD = "UNREAD";

  private final ChatroomRepository chatroomRepository;
  private final ChatroomFolderRepository chatroomFolderRepository;

  public ChatroomListResponse getAllChatrooms(String folderName) {
    List<Chatroom> chatrooms = findChatrooms(folderName);

    if (chatrooms.isEmpty()) {
      return ChatroomListResponse.from(List.of());
    }

    List<Long> chatroomIds = chatrooms.stream().map(Chatroom::getId).toList();
    Map<Long, List<String>> folderNamesByChatroomId =
        chatroomFolderRepository.findAllByChatroomIdInWithFolder(chatroomIds).stream()
            .collect(
                Collectors.groupingBy(
                    cf -> cf.getChatroom().getId(),
                    Collectors.mapping(
                        cf -> cf.getFolder().getFolderName().name(), Collectors.toList())));

    List<ChatroomResponse> responses =
        chatrooms.stream()
            .map(
                c ->
                    ChatroomResponse.from(
                        c, folderNamesByChatroomId.getOrDefault(c.getId(), List.of())))
            .toList();

    return ChatroomListResponse.from(responses);
  }

  @Transactional
  public ReadChatroomResponse readChatroom(Long chatroomId) {
    Chatroom chatroom =
        chatroomRepository
            .findById(chatroomId)
            .orElseThrow(() -> new BusinessException(ChatroomErrorCode.CHATROOM_NOT_FOUND));
    chatroom.markAsRead();
    return ReadChatroomResponse.from(chatroom);
  }

  private List<Chatroom> findChatrooms(String folderName) {
    // 미지정 또는 ALL → 전체 조회
    if (folderName == null || ALL.equals(folderName)) {
      return chatroomRepository.findAllOrderByLastMessageAtDesc();
    }
    // UNREAD → 안 읽은 채팅방
    if (UNREAD.equals(folderName)) {
      return chatroomRepository.findAllUnread();
    }
    // 나머지 → DB 폴더 필터 (잘못된 값은 IllegalArgumentException → 400 변환)
    try {
      FolderName folderNameEnum = FolderName.valueOf(folderName);
      return chatroomRepository.findAllByFolderName(folderNameEnum);
    } catch (IllegalArgumentException e) {
      throw new BusinessException(GlobalErrorCode.INVALID_REQUEST);
    }
  }
}
