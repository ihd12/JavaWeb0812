package org.zerock.tourist_springboot.notice.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.tourist_springboot.notice.domain.*;
import org.zerock.tourist_springboot.notice.dto.NoticeImageDTO;
import org.zerock.tourist_springboot.notice.dto.NoticeListAllDTO;

import java.util.List;
import java.util.stream.Collectors;

public class NoticeSearchImpl extends QuerydslRepositorySupport implements NoticeSearch {

  public NoticeSearchImpl() {
    super(Notice.class);
  }

  @Override
  public Page<Notice> searchAll(String keyword, Pageable pageable) {
    // Querydsl용 entity 선언
    QNotice notice = QNotice.notice;
    // JPQL SELECT쿼리문 생성
    JPQLQuery<Notice> query = from(notice);
    // keyword가 있을 경우 실행
    if (keyword != null) {
      // title열을 기준으로 like 검색
      query.where(notice.title.contains(keyword));
    }
    //JPQLQuery에 페이지네이션 설정
    this.getQuerydsl().applyPagination(pageable,query);
    // Query실행 후 결과를 저장
    List<Notice> list = query.fetch();
    // Query실행 결과의 개수 저장
    Long count = query.fetchCount();
    // Page<Notice> 데이터로 반환
    return new PageImpl<Notice>(list,pageable,count);
  }
  @Override
  public Page<NoticeListAllDTO> searchReplyAndImage(String keyword, Pageable pageable) {
    // Querydsl용 entity 선언
    QNotice notice = QNotice.notice;
    QReply reply = QReply.reply;
    // JPQL SELECT쿼리문 생성
//    SELECT * FROM notice n
//    LEFT JOIN reply r ON n.tno = r.notice_tno
//    WHERE n.title LIKE '%keyword%'
//    GROUP BY n.tno,n.title,n.content,n.view,n.moddate,n.regdate
//    ORDER BY tno DESC LIMIT 0,10;
    JPQLQuery<Notice> query = from(notice);
    query.leftJoin(reply).on(reply.notice.eq(notice));
    // keyword가 있을 경우 실행
    if (keyword != null) {
      // title열을 기준으로 like 검색
      query.where(notice.title.contains(keyword));
    }
    query.groupBy(notice);
    //JPQLQuery에 페이지네이션 설정
    this.getQuerydsl().applyPagination(pageable,query);
    // JPA 특정 열만 출력하는 것이 불가능하기 때문에 Tuple사용하여 특정 열 데이터만 출력하도록 설정
    // tno,title,content,view,moddate,regdate,COUNT(댓글)
    JPQLQuery<Tuple> tupleJPQLQuery = query.select(notice,reply.countDistinct());
    List<Tuple> tupleList = tupleJPQLQuery.fetch();

    // Query실행 후 결과를 저장
    List<NoticeListAllDTO> list = tupleList.stream().map(tuple -> {
      //tuple의 실행결과 중 notice데이터를 변수에 저장
      Notice notice1 = (Notice)tuple.get(notice);
      // tuple에서 reply.countDistinct()의 실행 결과를 변수에 저장
      long replyCount = tuple.get(1, Long.class);
      // 화면에서 사용할 NoticeListAllDTO에 데이터를 저장
      NoticeListAllDTO dto = NoticeListAllDTO.builder()
              .tno(notice1.getTno())
              .title(notice1.getTitle())
              .content(notice1.getContent())
              .view(notice1.getView())
              .replyCount(replyCount)
              .regDate(notice1.getRegDate())
              .modDate(notice1.getModDate())
              .build();
      // 이미지 데이터 저장
      List<NoticeImageDTO> imageDTOS = notice1.getImageSet().stream().sorted()
              .map(noticeImage -> NoticeImageDTO.builder()
                      .uuid(noticeImage.getUuid())
                      .fileName(noticeImage.getFileName())
                      .ord(noticeImage.getOrd())
                      .build()).collect(Collectors.toList());
      dto.setNoticeImages(imageDTOS);
      return dto;
    }).collect(Collectors.toList());

    // Query실행 결과의 개수 저장
    Long count = query.fetchCount();
    // Page<Notice> 데이터로 반환
    return new PageImpl<>(list,pageable,count);
  }
}
