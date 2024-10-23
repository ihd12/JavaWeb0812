package org.zerock.tourist.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
  // 페이지 번호
  private int page;
  // 페이지 당 데이터 수
  private int size;
  // 총 데이터 수
  private int total;
  //시작 페이지 번호
  private int start;
  //끝 페이지 번호
  private int end;
  // 이전 페이지의 존재 여부
  private boolean prev;
  // 다음 페이지의 존재 여부
  private boolean next;
  // 행의 데이터를 저장하는 dto 리스트
  private List<E> dtoList;

  @Builder(builderMethodName="withAll")
  public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();
    this.total = total;
    this.dtoList = dtoList;
    //(1/10) = 0.1 = 1 * 10 = 10 : 페이지가 1일때 10페이지까지 출력 한다
    //(11/10) = 1.1 = 2 * 10 = 20 : 페이지가 11일때 20페이지까지 출력 한다
    //(21/10) = 2.1 = 3 * 10 = 30 : 페이지가 21일때 30페이지까지 출력 한다
    //(255/10) = 25.5 = 26 * 10 = 260 : 페이지가 21일때 30페이지까지 출력 한다
    this.end = (int)(Math.ceil(this.page/10.0))*10;
    // 첫페이지를 설정하는 start
    this.start = this.end-9;
    // 총 페이지 수를 저장하기 위한 last 변수
    // 2565/10 = ceil(256.)5 = 257
    int last = (int)(Math.ceil((total/(double)size)));
    // 260 > 257 : end의 경우 마지막 258~300페이지 까지는 데이터가 없기 때문에
    // 마지막 페이지를 257로 설정하기 위해서 변경하는 기능
    this.end = end > last ? last : end;
    // start의 경우 페이지의 가장 작은수가 1이기 때문에 1보다 클 경우에만 이전 페이지 버튼이 나오도록 설정
    this.prev = this.start > 1;
    // next의 경우 페이지의 가장 큰 수보다 작은 페이지의 경우에만 다음 페이지 버튼이 나오도록 설정
    this.next = total > this.end * this.size;
  }
}











