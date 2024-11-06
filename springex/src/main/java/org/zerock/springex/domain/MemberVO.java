package org.zerock.springex.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
  private String id;
  private String pw;
  private String email1;
  private String email2;
  private LocalDateTime regDate;
}
