package org.sopt.kakaotalk.domain.chatroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.kakaotalk.domain.folder.entity.Folder;
import org.sopt.kakaotalk.global.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "chatroom_folder", uniqueConstraints = @UniqueConstraint(
        name = "uk_chatroom_folder",
        columnNames = {"chatroom_id", "folder_id"}
))
public class ChatroomFolder extends BaseTimeEntity {

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
