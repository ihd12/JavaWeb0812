package org.zerock.webmarket.notice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
  private int tno;
  private String title;
  private String content;
  private String view;
  private LocalDateTime createDate;
}
