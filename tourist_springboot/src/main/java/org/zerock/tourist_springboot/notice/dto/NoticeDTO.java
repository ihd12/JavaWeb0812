package org.zerock.tourist_springboot.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
  private Long tno;
  private String title;
  private String content;
  private int view;
  private List<String> fileNames;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
