package org.sopt.kakaotalk.domain.chatroom.repository;

public interface FolderUnreadCountProjection {
  Long getFolderId();

  Long getUnreadCount();
}
