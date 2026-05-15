package org.sopt.kakaotalk.domain.chatroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.kakaotalk.domain.folder.entity.Folder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "chatroom_folders",
    uniqueConstraints =
        @UniqueConstraint(
            name = "uk_chatroom_folders",
            columnNames = {"chatroom_id", "folder_id"}))
public class ChatroomFolder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chatroom_folder_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chatroom_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Chatroom chatroom;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "folder_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Folder folder;
}
