package org.zerock.tourist_springboot.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.notice.repository.search.NoticeSearch;

public interface NoticeRepository extends JpaRepository<Notice,Integer>, NoticeSearch {
}
