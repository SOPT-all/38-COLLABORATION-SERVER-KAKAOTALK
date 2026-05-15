package org.sopt.kakaotalk.domain.chatroom.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.kakaotalk.global.entity.BaseTimeEntity;

@Entity
@Getter
// 파라미터 없는 기본 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chatrooms")
public class Chatroom extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chatroom_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(name = "participant_count", nullable = false)
  private int participantCount;

  @Column(name = "last_message", nullable = false)
  private String lastMessage;

  @Column(name = "last_message_at", nullable = false)
  private LocalDateTime lastMessageAt;

  @Column(name = "unread_count", nullable = false)
  private int unreadCount;

  public void markAsRead() {
    this.unreadCount = 0;
  }
}
