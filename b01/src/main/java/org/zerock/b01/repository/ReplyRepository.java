package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  // JPQL에서는 테이블이나 열이름이 아닌 Entity의 이름을 사용하여야 합니다.
  @Query("SELECT r FROM Reply r WHERE r.board.bno = :bno")
  Page<Reply> listOfBoard(Long bno, Pageable pageable);
}
