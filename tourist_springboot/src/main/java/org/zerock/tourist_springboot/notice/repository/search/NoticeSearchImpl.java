package org.zerock.tourist_springboot.notice.repository.search;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.notice.domain.QNotice;

import java.util.List;

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
}
