package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface BoardService {
  Long register(BoardDTO dto);
  BoardDTO readOne(Long bno);
  void modify(BoardDTO dto);
  void remove(Long bno);
  PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
  PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
  PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

  // dto를 entity로 변환하는 경우 첨부파일 같은 내용이나 dto의 데이터와 entity의 데이터가 다른 경우 다른 내용에 맞게 변환하기 위해서 사용하는 메서드
  default Board dtoToEntity(BoardDTO boardDTO) {
    Board board = Board.builder()
        .bno(boardDTO.getBno())
        .title(boardDTO.getTitle())
        .content(boardDTO.getContent())
        .writer(boardDTO.getWriter())
        .subject(boardDTO.getSubject())
        .build();
    if(boardDTO.getFileNames() != null){
      boardDTO.getFileNames().forEach(fileName -> {
        // uuid_파일이름.확장자를 uuid와 파일이름으로 나누기 위한 split메서드 사용
        String[] arr = fileName.split("_",2);
        board.addImage(arr[0],arr[1]);
      });
    }
    return board;
  }
  // entity를 dto로 변환하는 메서드
  default BoardDTO entityToDTO(Board board) {
    BoardDTO boardDTO = BoardDTO.builder()
        .bno(board.getBno())
        .title(board.getTitle())
        .content(board.getContent())
        .writer(board.getWriter())
        .subject(board.getSubject())
        .modDate(board.getModDate())
        .regDate(board.getRegDate())
        .build();
    List<String> fileNames = board.getImageSet().stream().sorted()
        .map(boardImage -> boardImage.getUuid()+"_"+boardImage.getFileName())
        .collect(Collectors.toList());
    boardDTO.setFileNames(fileNames);
    return boardDTO;
  }
}









