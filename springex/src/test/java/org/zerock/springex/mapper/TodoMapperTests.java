package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {
  @Autowired
  private TodoMapper todoMapper;

  @Test
  public void testGetTime(){
    log.info(todoMapper.getTime());
  }
  @Test
  public void testSelectSearch(){
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .page(1)
        .size(10)
        .types(new String[]{"t","w"})
        .keyword("test1")
        .finished(false)
        .from(LocalDate.of(2021,12,01))
        .to(LocalDate.of(2024,10,20))
        .build();
    List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
    voList.forEach(vo -> log.info(vo));
    log.info(todoMapper.getCount(pageRequestDTO));
  }

}















