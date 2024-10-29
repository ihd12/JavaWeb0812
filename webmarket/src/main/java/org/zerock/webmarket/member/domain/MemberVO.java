package org.zerock.webmarket.member.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
  private String member_id;
  private String member_pw;
  private String name;
  private String phone;
  private String email1;
  private String email2;
  private String gender;
  private boolean agree;
  private LocalDate create_date;
}
