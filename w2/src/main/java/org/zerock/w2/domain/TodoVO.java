package org.zerock.w2.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
// ModelMapper 사용에 필수인 어노테이션 AllArgsConstructor, NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
  private Long tno;
  private String title;
  private LocalDate dueDate;
  private boolean finished;
}
