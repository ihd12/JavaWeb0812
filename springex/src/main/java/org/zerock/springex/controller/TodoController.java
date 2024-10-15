package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

@Log4j2
@RequiredArgsConstructor
@Controller
// 클래스의 uri경로 설정
@RequestMapping("/todo")
public class TodoController {
  private final TodoService todoService;

  // 메서드의 uri 경로 설정, 메서드 설정을 생략하면 GET메서드로 실행
  @RequestMapping("/list")
  public void list(){
    log.info("todo list...................");
  }
  @RequestMapping(value="/register", method=RequestMethod.GET)
  public void register(){
    log.info("todo register...................");
  }
  // POST메서드를 실행할 경우 method=RequestMethod.POST로 설정해야 합니다.
  @RequestMapping(value="/register", method = RequestMethod.POST)
  public String registerPost(TodoDTO todoDTO){
    log.info("todo register post ............");
    log.info(todoDTO);
    todoService.register(todoDTO);
    return "redirect:/list";
  }
}










