package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// 테이블을 만들기위한 자바 설정
@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity{
  @Id //기본키(PK) 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 설정
  private Long bno;
  @Column(length=500, nullable=false)
  private String title;
  @Column(columnDefinition = "TEXT", nullable=false)
  private String content;
  @Column(length=50, nullable=false)
  private String writer;
  @Column(length=50, nullable=false)
  private String subject;

  public void change(String title, String content,String subject){
    this.title = title;
    this.content = content;
    this.subject = subject;
  }
}
