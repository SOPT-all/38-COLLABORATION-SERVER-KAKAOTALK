package org.sopt.kakaotalk.domain.chatroom.repository;

import java.util.List;
import org.sopt.kakaotalk.domain.chatroom.entity.Chatroom;
import org.sopt.kakaotalk.domain.folder.entity.FolderName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

  // case 1: 필터 없음
  @Query(
      """
            SELECT c FROM Chatroom c
            ORDER BY c.lastMessageAt DESC
            """)
  List<Chatroom> findAllOrderByLastMessageAtDesc();

  // case 2. folderName만
  @Query(
      """
        SELECT c FROM Chatroom c
        JOIN ChatroomFolder cf ON cf.chatroom = c
        WHERE cf.folder.folderName = :folderName
        ORDER BY c.lastMessageAt DESC
    """)
  List<Chatroom> findAllByFolderName(@Param("folderName") FolderName folderName);

  // case 3: UNREAD (안 읽은 채팅방)
  @Query(
      """
          SELECT c FROM Chatroom c
          WHERE c.unreadCount > 0
          ORDER BY c.lastMessageAt DESC
      """)
  List<Chatroom> findAllUnread();

  // 모든 채팅방의 unread 합계 (ALL/UNREAD 가상 폴더용)
  @Query("SELECT COALESCE(SUM(c.unreadCount), 0) FROM Chatroom c")
  Long sumAllUnread();
}
