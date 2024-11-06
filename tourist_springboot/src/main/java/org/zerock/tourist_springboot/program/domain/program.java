package org.zerock.tourist_springboot.program.domain;

import jakarta.persistence.*;
import lombok.*;
import org.zerock.tourist_springboot.config.BaseEntity;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class program extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int no;
  @Column(length=200,nullable = false)
  private String title;
  @Column(length=300)
  private String text;
  @Column(length=300)
  private String subtext;
  @Column(length=100)
  private String schedule;
  @Column(length=300)
  private String img;
}
