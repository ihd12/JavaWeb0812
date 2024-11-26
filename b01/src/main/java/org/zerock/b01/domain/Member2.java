package org.zerock.b01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member2 extends BaseEntity{
  @Id
  @Column(length = 50)
  private String id;
  @Column(length = 50, nullable = false)
  private String pw;
  @Column(length = 50)
  private String email1;
  @Column(length = 50)
  private String email2;
}
