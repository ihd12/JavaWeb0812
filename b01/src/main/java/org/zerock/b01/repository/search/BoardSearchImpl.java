package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;
import org.zerock.b01.domain.QReply;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{
  public BoardSearchImpl(){
    super(Board.class);
  }

  @Override
  public Page<Board> searchAll(String subject, String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    // SELECT * FROM board
    JPQLQuery<Board> query = from(board);

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    // 타입이 존재하는지, 키워드가 존재하는지 확인하고 where을 생성

    if((types!=null && types.length>0) && keyword!=null){
      //동적 쿼리를 만들기 위한 클래스
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
    }
    if(subject != null && !subject.isEmpty()){
      booleanBuilder.and(board.subject.eq(subject));
    }
    // WHERE절 설정
    query.where(booleanBuilder);
    //paging 처리
    this.getQuerydsl().applyPagination(pageable, query);

    // fetch를 이용하여 위에서 만든 query문을 실행
    List<Board> list = query.fetch();
    // fetch실행 결과의 갯수
    Long count = query.fetchCount();

    return new PageImpl<Board>(list,pageable,count);

  }

  @Override
  public Page<BoardListReplyCountDTO> searchWithReplyCount(String subject,String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    // SELECT * FROM board
    JPQLQuery<Board> query = from(board);
    // LEFT JOIN reply ON  reply.board_bno = board.bno
    query.leftJoin(reply).on(reply.board.eq(board));
    query.groupBy(board);

    //동적 쿼리를 만들기 위한 클래스
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if((types!=null && types.length>0) && keyword!=null){

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
    }
    if(subject != null && !subject.isEmpty()){
      booleanBuilder.and(board.subject.eq(subject));
    }
    query.where(booleanBuilder);
    // bno>0
    query.where(board.bno.gt(0L));

    JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.
        bean(BoardListReplyCountDTO.class
        ,board.bno
        ,board.title
        ,board.writer
        ,board.regDate
        ,board.subject
        ,reply.count().as("replyCount")
        ));
    //paging 처리
    this.getQuerydsl().applyPagination(pageable, dtoQuery);

    // fetch를 이용하여 위에서 만든 query문을 실행
    List<BoardListReplyCountDTO> list = dtoQuery.fetch();
    // fetch실행 결과의 갯수
    Long count = dtoQuery.fetchCount();

    return new PageImpl<>(list,pageable,count);
  }

  @Override
  public Page<BoardListReplyCountDTO> searchWithAll(String subject, String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    JPQLQuery<Board> boardJPQLQuery = from(board);
    //left join : 댓글을 조회하기 위한 JOIN문 : 댓글은 단방향으로 설정했기 떄문에 board만 검색하면 댓글 데이터는 함께 조회하지 않습니다.
    boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));
    //페이징 설정
    getQuerydsl().applyPagination(pageable, boardJPQLQuery);
    //쿼리 실행
    List<Board> boardList = boardJPQLQuery.fetch();
    //조회한 데이터 출력
    boardList.forEach(board1 ->{
      System.out.println(board1.getBno());
      System.out.println(board1.getImageSet());
      System.out.println("-----------------------------");
    });
    return null;
  }
}









