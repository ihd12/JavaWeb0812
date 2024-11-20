package org.zerock.tourist_springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
  private String member_id;
  private String member_pw;
  private String name;
  private String phone;
  private String email1;
  private String email2;
  private String gender;
  private boolean agree;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
