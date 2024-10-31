package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{
  private final BoardRepository boardRepository;
  private final ModelMapper modelMapper;

  @Override
  public Long register(BoardDTO dto) {
    // 화면에서 받아온 dto데이터를 entity로 변환
    Board board = modelMapper.map(dto,Board.class);
    //레포지토리의 save메서드를 이용하여 insert문 실행 후 생성된 데이터의 bno를 반환
    Long bno = boardRepository.save(board).getBno();
    return bno;
  }

  @Override
  public BoardDTO readOne(Long bno) {
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    BoardDTO dto = modelMapper.map(board, BoardDTO.class);
    return dto;
  }

  @Override
  public void update(BoardDTO dto) {
    Optional<Board> result = boardRepository.findById(dto.getBno());
    Board board = result.orElseThrow();
    board.change(dto.getTitle(), dto.getContent());
    boardRepository.save(board);
  }

  @Override
  public void remove(Long bno) {
    boardRepository.deleteById(bno);
  }

  @Override
  public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
    //searchAll을 실행하기위한 데이터 변수화
    String[] types = pageRequestDTO.getTypes();
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("bno");
    // 레포지토리를 실행하여 검색 결과를 저장
    Page<Board> result = boardRepository.searchAll(types, keyword,pageable);
    // 검색결과의 Board데이터를 BoardDTO데이터로 변환
    List<BoardDTO> dtoList = result.getContent().stream()
        .map(board -> modelMapper.map(board, BoardDTO.class))
        .collect(Collectors.toList());
    // PageResponseDTO에 필요한 데이터를 설정하여 반환
    return PageResponseDTO.<BoardDTO>withAll()
        .pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total((int)result.getTotalElements())
        .build();
  }
}























