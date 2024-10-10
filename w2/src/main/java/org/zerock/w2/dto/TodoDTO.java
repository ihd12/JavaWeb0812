package org.zerock.w2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// VO : 데이터베이스의 테이블을 구현한 객체, 데이터베이스에서 데이터를 꺼내거나 취득할때 사용
// DTO : 화면에 출력하는 내용을 구현한 객체, 화면에 출력하거나 데이터를 변경
@Builder
// DTO클래스에 사용하는 @Data 어노테이션
// @Getter, @Setter, @ReqiredArgsConstructor, @toString, @HashCode, @equals
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
  private Long tno;
  private String title;
  private LocalDate dueDate;
  private boolean finished;
}
