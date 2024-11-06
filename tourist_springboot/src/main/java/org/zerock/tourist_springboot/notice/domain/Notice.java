package org.zerock.tourist_springboot.notice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.zerock.tourist_springboot.config.BaseEntity;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tno;
  @Column(length=255, nullable = false)
  private String title;
  @Column(columnDefinition = "TEXT",nullable = false)
  private String content;
  private int view;
  public void change(NoticeDTO dto){
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }
}
