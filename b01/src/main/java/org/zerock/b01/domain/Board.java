package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

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
  @Column(length=2000, nullable=false)
  private String content;
  @Column(length=50, nullable=false)
  private String writer;

  public void change(String title, String content){
    this.title = title;
    this.content = content;
  }
}
