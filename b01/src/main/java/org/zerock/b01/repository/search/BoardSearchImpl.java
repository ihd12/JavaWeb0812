package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{
  public BoardSearchImpl(){
    super(Board.class);
  }

  @Override
  public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    // SELECT * FROM board
    JPQLQuery<Board> query = from(board);

    // 타입이 존재하는지, 키워드가 존재하는지 확인하고 where을 생성
    if((types!=null && types.length>0) && keyword!=null){
      //동적 쿼리를 만들기 위한 클래스
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      for(String type : types){
        switch(type){
          case "t" :
            // 조건식 사이에 or 넣어주는 메서드 , 첫번째 조건식이라면 or을 넣지 않음
//            title LIKE %:keyword%
            booleanBuilder.or(board.title.contains(keyword));
            break;
          case "c":
            //content LIKE %:keyword%
            booleanBuilder.or(board.content.contains(keyword));
            break;
          case "w":
            //writer LIKE %:keyword%
            booleanBuilder.or(board.writer.contains(keyword));
            break;
//        types안에 tcw 모두 있었다면
//        title LIKE %keyword% or content LIKE %keyword% or writer LIKE %keyword%
        }
      }
      query.where(booleanBuilder);
    }

    //paging 처리
    this.getQuerydsl().applyPagination(pageable, query);

    // fetch를 이용하여 위에서 만든 query문을 실행
    List<Board> list = query.fetch();
    // fetch실행 결과의 갯수
    Long count = query.fetchCount();

    return new PageImpl<Board>(list,pageable,count);

  }
}









