package org.zerock.b01.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.BoardImage;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRespositoryTests {
  @Autowired
  private BoardRepository boardRepository;
  @Autowired
  private ReplyRepository replyRepository;

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
  @Transactional
  public void testSelect(){
    Long bno = 1L;
//    SELECT * FROM board WHERE bno=1;
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
    board.change("update..title", "update content 100", "Java");
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
  @Transactional
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
    String subject = "Java";
    String [] types = {"t","c"};
    String keyword = "1";
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.searchAll(subject,types,keyword,pageable);
    log.info(result.getTotalElements());
    log.info(result.getSize());
    log.info(result.getNumber());
    log.info(result.hasPrevious()+": " + result.hasNext());
    result.getContent().forEach(board -> log.info(board));
  }
  @Test
  public void testSearchReplyCount(){
    String [] types = {"t","c","w"};
    String keyword = "1";
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(null,types,keyword,pageable);
    log.info(result.getTotalPages());
    log.info(result.getSize());
    log.info(result.getNumber());
    log.info(result.hasPrevious()+": " + result.hasNext());
    result.getContent().forEach(board -> log.info(board));
  }
  @Test
  public void testInsertWithImages(){
    // board테이블에 들어갈 데이터
    Board board = Board.builder()
        .title("Image test")
        .content("첨부파일 테스트")
        .writer("tester")
        .subject("Java")
        .build();
    // BoardImage 엔티티 생성 후 Board엔티티에 저장
    for(int i=0; i<3; i++){
      board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
    }
    // Board 데이터와 BoardIamge데이터를 함께 저장
    boardRepository.save(board);
  }
  @Test
  // 트랜잭션 : SQL명령어의 최소 단위
  @Transactional // 메서드가 끝날때까지 세션을 종료하지 않음
  public void testReadWithImage(){
    Optional<Board> result = boardRepository.findById(1L);
    Board board = result.orElseThrow();
    log.info(board);
    log.info("---------------");
    log.info(board.getImageSet());
  }
  @Test
  public void testReadWithImages(){
    Optional<Board> result = boardRepository.findByIdWithImages(1L);
    Board board = result.orElseThrow();
    log.info(board);
    log.info("-------------");
    for(BoardImage boardImage : board.getImageSet()){
      log.info(boardImage);
    }
  }
  @Transactional
  @Commit
  @Test
  public void testModifyImages(){
    Optional<Board> result = boardRepository.findById(1L);
    Board board = result.orElseThrow();
    board.clearImages();
    for(int i=0; i<3; i++){
      board.addImage(UUID.randomUUID().toString(), "updatefile"+i+".jpg");
    }
    boardRepository.save(board);
  }

  @Test
  @Transactional
  @Commit
  public void testRemoveAll(){
    Long bno = 1L;
    replyRepository.deleteByBoard_bno(bno);
    boardRepository.deleteById(bno);
  }
  @Test
  public void testInsertAll(){
    for(int i=1; i<=100; i++){
      Board board = Board.builder()
          .title("title.."+i)
          .content("content.."+i)
          .writer("writer.."+i)
          .subject("Java")
          .build();
      for(int j=0; j<3; j++){
        if(i%5 == 0){
          continue;
        }
        board.addImage(UUID.randomUUID().toString(), "file"+j+".jpg");
      }
      boardRepository.save(board);
    }
  }
  @Transactional
  @Test
  public void testSearchImageReplyCount(){
    Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
    boardRepository.searchWithAll(null, null, null,pageable);
  }
}














