package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
  INSTANCE;
  private TodoDAO dao;
  private ModelMapper modelMapper;
  TodoService(){
    dao = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.get();
  }
  public void register(TodoDTO todoDTO) throws Exception{
    //modelMapper를 이용하여 DTO를 VO변경하는 코드
    //                          변경전 데이터, 변경 후의 클래스
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
//    System.out.println("todoVO : " + todoVO);
    log.info(todoVO);
    dao.insert(todoVO);
  }
  public List<TodoDTO> listAll() throws Exception{
    //데이터베이스에서 tbl_todo의 모든 데이터를 취득
    List<TodoVO> voList = dao.selectAll();
    log.info("voList.............");
    log.info(voList);
    // voList의 TodoVO객체를 TodoDTO객체로 바꾸어서 dtoList에 저장
    //                      List에서 여러가지 기능이 사용가능한 stream으로 변경
    List<TodoDTO> dtoList = voList.stream()
        // map(데이터를 하나를 의미하는 변수 -> 실행하고자 하는 코드) : 리스트의 데이터 하나하나 마다 반복해주는 기능
        .map(vo->modelMapper.map(vo, TodoDTO.class))
        // collect(변경하고싶은 타입) : 위의 map에서 실행한 결과물을 List타입 안에 저장
        .collect(Collectors.toList());
    return dtoList;
  }
  public TodoDTO getOne(Long tno) throws Exception{
    //dao의 selectOne(tno)을 실행하여 데이터 받기
    TodoVO vo = dao.selectOne(tno);
    //selectOne(tno)에서 받은 VO데이터를  DTO로 변환하기 (ModelMapper 사용)
    TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
    // return 으로 dto 돌려주기
    return dto;
  }

  public void modify(TodoDTO todoDTO) throws Exception{
    // DTO를 dao.update()에서 쓰기위해 VO로 변환
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    log.info(todoVO);
    // UPDATE문을 실행하도록 dao를 실행
    dao.update(todoVO);
  }
}










