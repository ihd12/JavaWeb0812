package org.zerock.tourist.notice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
  private int tno;
  private String title;
  private String content;
  private int view;
  private LocalDateTime createDate;
}
