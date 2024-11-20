package org.zerock.tourist_springboot.notice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.zerock.tourist_springboot.config.BaseEntity;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tno;
  @Column(length=255, nullable = false)
  private String title;
  @Column(columnDefinition = "TEXT",nullable = false)
  private String content;
  private int view;
  @OneToMany(mappedBy = "notice"
          , fetch = FetchType.LAZY
          , cascade = {CascadeType.ALL}
          , orphanRemoval = true)
  @Builder.Default
  @BatchSize(size=20)
  private Set<NoticeImage> imageSet = new HashSet<>();
  public void addImage(String uuid, String fileName) {
    NoticeImage noticeImage = NoticeImage.builder()
            .uuid(uuid)
            .fileName(fileName)
            // board_bno 설정
            .notice(this)
            //0번부터 시작하는 순서를 설정
            .ord(imageSet.size())
            .build();
    imageSet.add(noticeImage);
  }
  //게시글에있는 모든 이미지 파일을 삭제
  public void clearImages(){
    imageSet.forEach(noticeImage -> noticeImage.changeNotice(null));
    this.imageSet.clear();
  }
  public void change(NoticeDTO dto){
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }
}
