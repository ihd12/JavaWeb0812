package org.zerock.api01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.TodoDTO;
import org.zerock.api01.repository.TodoRepository;
import org.zerock.api01.service.TodoService;

import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
  private final TodoService todoService;
  private final TodoRepository todoRepository;

  @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Map<String,Long> register(@RequestBody TodoDTO todoDTO){
    log.info(todoDTO);
    Long tno = todoService.register(todoDTO);
    return Map.of("tno",tno);
  }
  @GetMapping("/{tno}")
  public TodoDTO read(@PathVariable("tno") Long tno){
    log.info("read tno: " + tno);
    return todoService.read(tno);
  }
  @GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
  public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){
    return todoService.list(pageRequestDTO);
  }
  @PutMapping(value="/{tno}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Map<String,String> modify(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO){
    todoDTO.setTno(tno);
    todoService.modify(todoDTO);
    return Map.of("result","success");
  }
  @DeleteMapping("/{tno}")
  public Map<String,String> delete(@PathVariable("tno") Long tno){
    todoService.remove(tno);
    return Map.of("result","success");
  }
}
