package org.sopt.kakaotalk.domain.folder.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.kakaotalk.global.entity.BaseTimeEntity;

@Entity
@Table(name = "folders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "folder_id")
  private Long folderId;

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false)
  private FolderName folderName;

  @Enumerated(EnumType.STRING)
  @Column(name = "icon", nullable = false)
  private FolderIcon folderIcon;
}
