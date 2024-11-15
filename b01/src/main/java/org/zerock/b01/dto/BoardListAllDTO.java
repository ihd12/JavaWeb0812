package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListAllDTO {
  private Long bno;
  private String title;
  private String writer;
  private LocalDateTime regDate;
  private Long replyCount;
  private String subject;
  // Board 엔티티에 존재하는 imageSet의 데이터를 저장하는 List
  private List<BoardImageDTO> boardImages;
}












