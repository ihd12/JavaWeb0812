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

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRespositoryTests {
  @Autowired
  private BoardRepository boardRepository;
  @Test
  public void testInsert(){
//    1~100까지의 board 테이블 데이터 생성
    IntStream.rangeClosed(1,100).forEach(i ->{
      Board board = Board.builder()
          .title("title..." + i)
          .content("content..." + i)
          .writer("user"+(i%10))
          .build();
      // 위에서 board객체를 save메서드를 이용하여 데이터베이스에 저장
      Board result = boardRepository.save(board);
      log.info("BNO : " + result.getBno());
    });
  }
  @Test
  public void testSelect(){
    Long bno = 100L;
//    SELECT * FROM board WHERE bno=100;
    Optional<Board> result = boardRepository.findById(bno);
    // 데이터가 있으면 board 데이터를 저장
    Board board = result.orElseThrow();
    log.info(board);
  }
  @Test
  public void testUpdate(){
    Long bno = 100L;
    // 1. 기존의 데이터를 데이터베이스에서 취득
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    // 2. 변경되는 데이터를 데이터베이스에서 취득한 데이터에 적용
    board.change("update..title", "update content 100");
    // 3. 이미 존재하는 데이터의 경우 save실행 시 update문이 실행됨
    boardRepository.save(board);
  }
  @Test
  public void testDelete(){
    Long bno = 1L;
    // DELETE FROM board WHERE bno=1
    boardRepository.deleteById(bno);
  }
  @Test
  public void testPaging(){
    // Pageable : 페이지번호, 사이즈, 정렬방식
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.findAll(pageable);
    // 테이블 안에 몇개의 데이터가 있는지, 총 데이터 개수
    log.info("total count: " + result.getTotalElements());
    // 총 페이지 수
    log.info("total pages: " + result.getTotalPages());
    // 현재 페이지
    log.info("page number: " + result.getNumber());
    // 현재 페이지의 사이즈
    log.info("page size: " + result.getSize());
    // getContent() 메서드를 이용하여 검색한 데이터도 출력 가능
    List<Board> todoList = result.getContent();
    todoList.forEach(board -> log.info(board));
  }
  @Test
  public void testQueryMethod(){
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.findByTitleContainingOrderByBnoDesc("1",pageable);
    List<Board> todoList = result.getContent();
    todoList.forEach(board -> log.info(board));
  }
  @Test
  public void testQuery(){
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.findKeyword("1",pageable);
    List<Board> todoList = result.getContent();
    todoList.forEach(board -> log.info(board));
  }
  @Test
  public void testGetTime(){
    log.info(boardRepository.getTime());
  }
  @Test
  public void testSearchAll(){
    String [] types = {"t","c"};
    String keyword = "1";
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
    log.info(result.getTotalElements());
    log.info(result.getSize());
    log.info(result.getNumber());
    log.info(result.hasPrevious()+": " + result.hasNext());
    result.getContent().forEach(board -> log.info(board));
  }
}














