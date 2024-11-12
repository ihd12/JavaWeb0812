package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
  // 댓글의 PK
  private Long rno;
  // board의 PK를 FK로
  @NotNull
  private Long bno;
  // 댓글 내용
  @NotEmpty
  private String replyText;
  // 댓글 작성자
  @NotEmpty
  private String replyer;
  // 등록일, 수정일
  private LocalDateTime regDate, modDate;
}









