package org.zerock.tourist_springboot.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
  private int tno;
  private String title;
  private String content;
  private int view;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
