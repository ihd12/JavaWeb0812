package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardListAllDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;

public interface BoardSearch {
  Page<Board> searchAll(String subject,String[] types, String keyword, Pageable pageable);
  Page<BoardListReplyCountDTO> searchWithReplyCount(String subject,String[] types, String keyword, Pageable pageable);
  Page<BoardListAllDTO> searchWithAll(String subject, String[] types, String keyword, Pageable pageable);
}
