package org.zerock.w2.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
  // 변수 이름은 데이터베이스와 일치하도록 작성
  private String mid;
  private String mpw;
  private String mname;
}
