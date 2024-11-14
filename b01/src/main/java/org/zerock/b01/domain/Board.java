package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  //orphanRemoval = true : boardImage데이터를 실제로 데이터베이스에서 삭제하는 설정
  @OneToMany(mappedBy="board",
      cascade={CascadeType.ALL},
      fetch=FetchType.LAZY,
      orphanRemoval = true)
  @Builder.Default
  @BatchSize(size=20)
  private Set<BoardImage> imageSet = new HashSet<>();
  // board 엔티티에 이미지를 추가하는 메서드
  public void addImage(String uuid, String fileName) {
    BoardImage boardImage = BoardImage.builder()
        .uuid(uuid)
        .fileName(fileName)
        // board_bno 설정
        .board(this)
        //0번부터 시작하는 순서를 설정
        .ord(imageSet.size())
        .build();
    imageSet.add(boardImage);
  }
  //게시글에있는 모든 이미지 파일을 삭제
  public void clearImages(){
    imageSet.forEach(boardImage -> boardImage.changeBoard(null));
    this.imageSet.clear();
  }

  public void change(String title, String content,String subject){
    this.title = title;
    this.content = content;
    this.subject = subject;
  }
}
