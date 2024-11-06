package org.zerock.tourist_springboot.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.zerock.tourist_springboot.config.BaseEntity;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
  @Id
  @Column(length=50)
  private String member_id;
  @Column(length=50,nullable = false)
  private String member_pw;
  @Column(length=50)
  private String name;
  @Column(length=50)
  private String phone;
  @Column(length=50)
  private String email1;
  @Column(length=50)
  private String email2;
  @Column(length=6)
  private String gender;
  private boolean agree;
}
