package org.sopt.kakaotalk.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
// 이 클래스 자체는 테이블 X, 이 클래스의 필드들은 자식 엔티티 테이블에 컬럼으로 매핑됨
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// 모든 도메인 엔티티에 대해 생성/수정 시간 자동 기록
// BaseTimeEntity 자체로는 의미 X, 다른 엔티티가 상속해서 사용하는 것이 목적이므로 abstract
public abstract class BaseTimeEntity {

  // 엔티티가 처음 저장될 때만 현재 시각이 자동으로 들어감
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  // 엔티티가 저장되거나 수정될 때마다 현재 시각이 자동으로 들어감
  @LastModifiedDate private LocalDateTime updatedAt;
}
