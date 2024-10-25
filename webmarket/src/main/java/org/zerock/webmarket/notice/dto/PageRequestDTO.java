package org.zerock.webmarket.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
  private int page=1;
  private int size=10;
  private String keyword;
  private String link;
  public int getSkip(){
    // 1페이지 (1-1)*10=0
    // 2페이지 (2-1)*10=10
    return (page - 1) * size;
  }
  public String getLink(){
    if(link==null){
      StringBuilder str = new StringBuilder();
      str.append("page="+this.page);
      str.append("&size="+this.size);
      if(keyword!=null){
        str.append("&keyword="+this.keyword);
      }
      this.link = str.toString();
    }
    return this.link;
  }

}












