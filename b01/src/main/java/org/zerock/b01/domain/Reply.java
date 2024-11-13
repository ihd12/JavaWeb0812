package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
// 테이블 설정 및 인덱스 설정
@Table(name="Reply", indexes = {@Index(name="idx_reply_board_bno", columnList = "board_bno")})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// StackOverflow에러를 방지하기 위해
@ToString(exclude="board")
public class Reply extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;
  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;
  private String replyText;
  private String replyer;
  public void changeText(String text){
    this.replyText = text;
  }
}
