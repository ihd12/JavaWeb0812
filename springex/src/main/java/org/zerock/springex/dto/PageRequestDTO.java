package org.zerock.springex.dto;

import lombok.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
  // 페이지 수
  @Builder.Default
  @Min(value = 1)
  @Positive
  private int page = 1;
  // 한 페이지에 출력할 데이터이 수
  @Builder.Default
  @Min(value = 10)
  @Max(value = 100)
  @Positive
  private int size = 10;
  private String link;

  // 제목과 작성자 검색 여부
  private String[] types;
  // 검색할 키워드
  private String keyword;
  // 완료여부
  private boolean finished;
  // 시작 기간
  private LocalDate from;
  // 종료 기간
  private LocalDate to;

  // LIMIT에서 사용할 값을 설정하는 메서드
  public int getSkip(){
    return (page-1) * 10;
  }
  public String getLink(){
    if(link == null){
      StringBuilder builder = new StringBuilder();
      builder.append("page=" + this.page);
      builder.append("&size=" + this.size);
      if(finished){
        builder.append("&finished=on");
      }
      if(types != null && types.length > 0){
        for(String type : types){
          builder.append("&types=" + type);
        }
      }
      if(keyword != null){
        try{
          builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
        }catch(UnsupportedEncodingException e){
          e.printStackTrace();
        }
      }
      if(from != null){
        builder.append("&from=" + from.toString());
      }
      if(to != null){
        builder.append("&to=" + to.toString());
      }
      link = builder.toString();
    }
    return link;
  }
  public boolean checkType(String type){
    if(types == null || types.length == 0){
      return false;
    }
    return Arrays.stream(types).anyMatch(type::equals);
  }
}











