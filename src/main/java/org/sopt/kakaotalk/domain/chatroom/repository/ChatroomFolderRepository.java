package org.sopt.kakaotalk.domain.chatroom.repository;

import java.util.List;
import org.sopt.kakaotalk.domain.chatroom.entity.ChatroomFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatroomFolderRepository extends JpaRepository<ChatroomFolder, Long> {

  @Query(
      """
        SELECT cf FROM ChatroomFolder cf
        JOIN FETCH cf.folder
        WHERE cf.chatroom.id IN :chatroomIds
    """)
  List<ChatroomFolder> findAllByChatroomIdInWithFolder(
      @Param("chatroomIds") List<Long> chatroomIds);

  // 폴더별 unread 합계. ALL/UNREAD 가상 폴더는 별도 (ChatroomRepository.sumAllUnread)
  @Query(
      """
        SELECT cf.folder.folderId AS folderId, COALESCE(SUM(cf.chatroom.unreadCount), 0) AS unreadCount
        FROM ChatroomFolder cf
        GROUP BY cf.folder.folderId
    """)
  List<FolderUnreadCountProjection> findUnreadCountByFolder();
}
