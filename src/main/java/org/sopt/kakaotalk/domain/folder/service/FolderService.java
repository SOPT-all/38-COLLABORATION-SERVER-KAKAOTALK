package org.sopt.kakaotalk.domain.folder.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomFolderRepository;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomRepository;
import org.sopt.kakaotalk.domain.chatroom.repository.FolderUnreadCountProjection;
import org.sopt.kakaotalk.domain.folder.dto.response.FolderListResponse;
import org.sopt.kakaotalk.domain.folder.entity.Folder;
import org.sopt.kakaotalk.domain.folder.repository.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

  private final FolderRepository folderRepository;
  private final ChatroomFolderRepository chatroomFolderRepository;
  private final ChatroomRepository chatroomRepository;

  public FolderListResponse getFolders() {
    List<Folder> folders = folderRepository.findAllByOrderByFolderIdAsc();

    Map<Long, Long> unreadByFolderId =
        chatroomFolderRepository.findUnreadCountByFolder().stream()
            .collect(
                Collectors.toMap(
                    FolderUnreadCountProjection::getFolderId,
                    FolderUnreadCountProjection::getUnreadCount));

    Long totalUnread = chatroomRepository.sumAllUnread();

    return FolderListResponse.from(folders, unreadByFolderId, totalUnread);
  }
}
