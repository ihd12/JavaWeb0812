package org.zerock.b01.domain;

import groovy.beans.Bindable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {
  @Id
  private String uuid;
  private String fileName;
  // 파일의 순서를 결정하는 값 ord : 오름차순, 내림차순 설정에 사용
  private int ord;
  @ManyToOne
  private Board board;

  @Override
  public int compareTo(BoardImage other) {
    return this.ord - other.ord; // 오름차순 설정
    // return other.ord - this.ord; // 내림차순 설정
  }
  public void changeBoard(Board board){
    this.board = board;
  }
}
