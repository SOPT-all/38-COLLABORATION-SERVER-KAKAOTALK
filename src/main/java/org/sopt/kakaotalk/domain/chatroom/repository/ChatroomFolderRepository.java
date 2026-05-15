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
}
