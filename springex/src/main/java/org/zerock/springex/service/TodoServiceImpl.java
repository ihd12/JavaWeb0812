package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl  implements TodoService{
  private final TodoMapper todoMapper;
  private final ModelMapper modelMapper;
  @Override
  public void register(TodoDTO todoDTO) {
    log.info(modelMapper);
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    log.info(todoVO);
    todoMapper.insert(todoVO);
  }

  @Override
  public List<TodoDTO> getAll() {
    //tbl_todo의 모든 데이터를 voList에 저장
    List<TodoVO> voList = todoMapper.selectAll();
    // voList의 TodoVO데이터를 TodoDTO데이터로 변환하여  dtoList에 저장
    List<TodoDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo,TodoDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }

  @Override
  public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
    // Mapper를 이용하여 tbl_todo 테이블의 페이징 데이터 취득
    List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
    // TodoVO로 되어있는 List를 DTO List로 변환
    List<TodoDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo, TodoDTO.class))
        .collect(Collectors.toList());
    // 화면에 돌려줄 데이터인 PageResponseDTO를 설정
    PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
        .pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total(todoMapper.getCount(pageRequestDTO))
        .build();
    return pageResponseDTO;
  }

  @Override
  public TodoDTO getOne(Long tno) {
    TodoVO todoVO = todoMapper.selectOne(tno);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
    return todoDTO;
  }

  @Override
  public void remove(Long tno) {
    todoMapper.delete(tno);
  }

  @Override
  public void modify(TodoDTO todoDTO) {
    TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);
    todoMapper.update(todoVO);
  }

}
