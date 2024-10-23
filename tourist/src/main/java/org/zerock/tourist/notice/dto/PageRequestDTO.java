package org.zerock.tourist.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
  @Builder.Default
  private int page = 1;
  @Builder.Default
  private int size = 10;
  private String keyword;
  private String link;

  public int getSkip(){
    return (page-1)*10;
  }

  public String getLink(){
    if(link == null){
      StringBuilder builder = new StringBuilder();
      builder.append("page="+ this.page);
      builder.append("&size="+ this.size);
      if(keyword != null){
        try{
          builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    }
    return link;
  }
}
