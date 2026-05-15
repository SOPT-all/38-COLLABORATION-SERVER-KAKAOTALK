package org.sopt.kakaotalk.domain.folder.dto.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.lang.reflect.Constructor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.kakaotalk.domain.folder.entity.Folder;
import org.sopt.kakaotalk.domain.folder.entity.FolderIcon;
import org.sopt.kakaotalk.domain.folder.entity.FolderName;
import org.springframework.test.util.ReflectionTestUtils;

class FolderListResponseTest {

  @Test
  @DisplayName("DB 폴더 앞에 ALL, UNREAD 동적 폴더를 추가한다")
  void fromAddsDynamicFoldersBeforeDatabaseFolders() throws Exception {
    // given
    Folder soptFolder = createFolder(1L, FolderName.SOPT, FolderIcon.ICON_RYAN);
    Folder schoolFolder = createFolder(2L, FolderName.SCHOOL, FolderIcon.ICON_BRIEFCASE);

    // when
    FolderListResponse response = FolderListResponse.from(List.of(soptFolder, schoolFolder));

    // then
    assertThat(response.folders())
        .extracting(FolderResponse::folderId, FolderResponse::name, FolderResponse::icon)
        .containsExactly(
            tuple(-1L, "ALL", "ICON_NONE"),
            tuple(-2L, "UNREAD", "ICON_SPEECHBUBBLE"),
            tuple(1L, "SOPT", "ICON_RYAN"),
            tuple(2L, "SCHOOL", "ICON_BRIEFCASE"));
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
