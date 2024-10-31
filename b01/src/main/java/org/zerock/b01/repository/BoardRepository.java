package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
  Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

  @Query("SELECT b FROM Board b WHERE b.title LIKE CONCAT('%',:keyword,'%')")
  Page<Board> findKeyword(String keyword, Pageable pageable);

  //nativeQuery : 특정 데이터베이스에서 사용 가능한 쿼리문을 실행하도록 설정
  @Query(value="SELECT NOW()", nativeQuery = true)
  String getTime();
}
