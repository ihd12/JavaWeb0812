package org.zerock.tourist_springboot.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.tourist_springboot.notice.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long>{
    @Query("SELECT nr FROM Reply nr WHERE nr.notice.tno = :tno")
    Page<Reply> listOfNotice(Long tno, Pageable pageable);
    void deleteByNotice_tno(Long tno);
}
