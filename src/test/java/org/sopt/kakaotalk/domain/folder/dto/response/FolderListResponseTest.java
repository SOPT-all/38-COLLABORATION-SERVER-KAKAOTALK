package org.sopt.kakaotalk.domain.folder.dto.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.kakaotalk.domain.folder.entity.Folder;
import org.sopt.kakaotalk.domain.folder.entity.FolderIcon;
import org.sopt.kakaotalk.domain.folder.entity.FolderName;
import org.springframework.test.util.ReflectionTestUtils;

class FolderListResponseTest {

  @Test
  @DisplayName("DB 폴더 앞에 ALL, UNREAD 동적 폴더를 추가하고 unreadCount를 매핑한다")
  void fromAddsDynamicFoldersBeforeDatabaseFolders() throws Exception {
    // given
    Folder soptFolder = createFolder(1L, FolderName.SOPT, FolderIcon.ICON_RYAN);
    Folder schoolFolder = createFolder(2L, FolderName.SCHOOL, FolderIcon.ICON_BRIEFCASE);
    Map<Long, Long> unreadByFolderId = Map.of(1L, 12L, 2L, 349L);
    Long totalUnread = 361L;

    // when
    FolderListResponse response =
        FolderListResponse.from(List.of(soptFolder, schoolFolder), unreadByFolderId, totalUnread);

    // then
    assertThat(response.folders())
        .extracting(
            FolderResponse::folderId,
            FolderResponse::name,
            FolderResponse::icon,
            FolderResponse::unreadCount)
        .containsExactly(
            tuple(-1L, "ALL", "ICON_NONE", 361L),
            tuple(-2L, "UNREAD", "ICON_SPEECHBUBBLE", 361L),
            tuple(1L, "SOPT", "ICON_RYAN", 12L),
            tuple(2L, "SCHOOL", "ICON_BRIEFCASE", 349L));
  }

  @Test
  @DisplayName("unreadByFolderId에 없는 폴더는 unreadCount가 0으로 매핑된다")
  void fromMapsZeroWhenFolderNotInUnreadMap() throws Exception {
    // given
    Folder soptFolder = createFolder(1L, FolderName.SOPT, FolderIcon.ICON_RYAN);
    Map<Long, Long> unreadByFolderId = Map.of(); // 비어있음
    Long totalUnread = 0L;

    // when
    FolderListResponse response =
        FolderListResponse.from(List.of(soptFolder), unreadByFolderId, totalUnread);

    // then
    assertThat(response.folders())
        .extracting(FolderResponse::folderId, FolderResponse::unreadCount)
        .containsExactly(tuple(-1L, 0L), tuple(-2L, 0L), tuple(1L, 0L));
  }

  private Folder createFolder(Long folderId, FolderName folderName, FolderIcon folderIcon)
      throws Exception {
    Constructor<Folder> constructor = Folder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Folder folder = constructor.newInstance();
    ReflectionTestUtils.setField(folder, "folderId", folderId);
    ReflectionTestUtils.setField(folder, "folderName", folderName);
    ReflectionTestUtils.setField(folder, "folderIcon", folderIcon);
    return folder;
  }
}
