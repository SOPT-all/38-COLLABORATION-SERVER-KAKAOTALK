package org.sopt.kakaotalk.domain.folder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomFolderRepository;
import org.sopt.kakaotalk.domain.chatroom.repository.ChatroomRepository;
import org.sopt.kakaotalk.domain.folder.dto.response.FolderListResponse;
import org.sopt.kakaotalk.domain.folder.repository.FolderRepository;

@ExtendWith(MockitoExtension.class)
class FolderServiceTest {

  @Mock private FolderRepository folderRepository;
  @Mock private ChatroomFolderRepository chatroomFolderRepository;
  @Mock private ChatroomRepository chatroomRepository;

  @InjectMocks private FolderService folderService;

  @Test
  @DisplayName("폴더 목록 조회 시 folderId 오름차순 조회 결과에 동적 폴더를 포함해 반환한다")
  void getFoldersReturnsDynamicFoldersAndDatabaseFolders() {
    // given
    when(folderRepository.findAllByOrderByFolderIdAsc()).thenReturn(List.of());
    when(chatroomFolderRepository.findUnreadCountByFolder()).thenReturn(List.of());
    when(chatroomRepository.sumAllUnread()).thenReturn(0L);

    // when
    FolderListResponse response = folderService.getFolders();

    // then
    verify(folderRepository).findAllByOrderByFolderIdAsc();
    verify(chatroomFolderRepository).findUnreadCountByFolder();
    verify(chatroomRepository).sumAllUnread();
    assertThat(response.folders()).hasSize(2);
    assertThat(response.folders().get(0).name()).isEqualTo("ALL");
    assertThat(response.folders().get(0).unreadCount()).isEqualTo(0L);
    assertThat(response.folders().get(1).name()).isEqualTo("UNREAD");
    assertThat(response.folders().get(1).unreadCount()).isEqualTo(0L);
  }
}
