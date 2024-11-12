package org.zerock.b01.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
  @NotEmpty
  private String id;
  @NotEmpty
  private String pw;
  private String email1;
  private String email2;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
