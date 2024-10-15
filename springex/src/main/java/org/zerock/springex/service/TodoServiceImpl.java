package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
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

}
