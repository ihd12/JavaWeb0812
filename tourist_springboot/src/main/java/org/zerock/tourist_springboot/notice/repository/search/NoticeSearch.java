package org.zerock.tourist_springboot.notice.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.tourist_springboot.notice.domain.Notice;

public interface NoticeSearch {
  Page<Notice> searchAll(String keyword, Pageable pageable);
}
