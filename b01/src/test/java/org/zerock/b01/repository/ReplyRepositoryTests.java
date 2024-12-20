package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
  @Autowired
  private ReplyRepository replyRepository;
  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void testInsert(){
    Long bno = 1L;
    Board board = boardRepository.findById(bno).orElseThrow();
    // Reply 엔티티 안에 Board객체가 있기 때문에 Board객체를 만들어서 추가
    Reply reply = Reply.builder()
        .board(board)
        .replyText("댓글......")
        .replyer("replyer1")
        .build();
    replyRepository.save(reply);
  }
  @Test
  public void testBoardReplies(){
    Long bno = 1L;
    Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());
    Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
    result.getContent().forEach(reply -> log.info(reply));
  }
}













