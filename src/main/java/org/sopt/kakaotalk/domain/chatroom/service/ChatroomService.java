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
import org.sopt.kakaotalk.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatroomService {

  private final ChatroomRepository chatroomRepository;
  private final ChatroomFolderRepository chatroomFolderRepository;

  public ChatroomListResponse getAllChatrooms(FolderName folderName, boolean unreadOnly) {
    List<Chatroom> chatrooms = findChatrooms(folderName, unreadOnly);

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

  private List<Chatroom> findChatrooms(FolderName folderName, boolean unreadOnly) {
    if (folderName == null && !unreadOnly) {
      return chatroomRepository.findAllOrderByLastMessageAtDesc();
    }
    if (folderName != null && !unreadOnly) {
      return chatroomRepository.findAllByFolderName(folderName);
    }
    if (folderName == null && unreadOnly) {
      return chatroomRepository.findAllUnread();
    }
    return chatroomRepository.findAllByFolderNameAndUnread(folderName);
  }
}
