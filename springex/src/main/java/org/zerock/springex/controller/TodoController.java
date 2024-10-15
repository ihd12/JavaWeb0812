package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
// 클래스의 uri경로 설정
@RequestMapping("/todo")
public class TodoController {
  private final TodoService todoService;

  // 메서드의 uri 경로 설정, 메서드 설정을 생략하면 GET메서드로 실행
  @RequestMapping("/list")
  public void list(Model model){
    log.info("todo list...................");
    List<TodoDTO> dtoList = todoService.getAll();
    model.addAttribute("dtoList", dtoList);
  }
  @RequestMapping(value="/register", method=RequestMethod.GET)
  public void register(){
    log.info("todo register...................");
  }
  // POST메서드를 실행할 경우 method=RequestMethod.POST로 설정해야 합니다.
  @RequestMapping(value="/register", method = RequestMethod.POST)
  // @Valid 어노테이션을 이용하여 TodoDTO을 검증하고
  // 검증 결과를 BindingResult에 저장한다.
  public String registerPost(@Valid TodoDTO todoDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
    log.info("todo register post ............");
    // 에러가 있을 경우 hasErrors 메서드가 true가 되어서 if문 실행
    if(bindingResult.hasErrors()){
      log.info("has errors........");
      // 에러의 내용을 register 페이지에 전달하는 addFlashAttribute
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      // register페이지로 리다이렉트 실행
      return "redirect:/todo/register";
    }
    log.info(todoDTO);
    todoService.register(todoDTO);
    return "redirect:/todo/list";
  }
}










