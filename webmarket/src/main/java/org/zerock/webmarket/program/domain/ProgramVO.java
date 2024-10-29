package org.zerock.webmarket.program.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramVO {
  private int no;
  private String title;
  private String text;
  private String subtext;
  private String schedule;
  private String img;
  private LocalDate create_date;
}
