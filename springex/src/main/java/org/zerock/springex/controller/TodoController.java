package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
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
  public void list(Model model,
                   @Valid PageRequestDTO pageRequestDTO,
                   BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      pageRequestDTO = PageRequestDTO.builder().build();
    }
    log.info("todo list...................");
    PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
    model.addAttribute("responseDTO", responseDTO);
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
  @GetMapping({"/read","/modify"})
  // pageRequestDTO의 경우 객체이기 때문에 자동으로 model에 포함된다.
  public void read(Model model, Long tno, PageRequestDTO pageRequestDTO){
    TodoDTO dto = todoService.getOne(tno);
    log.info(dto);
    model.addAttribute("dto", dto);
  }
  @PostMapping("/remove")
  public String remove(Long tno,
                       PageRequestDTO pageRequestDTO,
                       RedirectAttributes redirectAttributes){
    todoService.remove(tno);
    return "redirect:/todo/list?" + pageRequestDTO.getLink();
  }
  @PostMapping("/modify")
  public String modify(PageRequestDTO pageRequestDTO,
                      @Valid TodoDTO todoDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
    // DTO에 맞는 데이터가 들어왔는 확인하는 if문
    if(bindingResult.hasErrors()){
      log.info("has errors........");
      // 어떤 데이터에서 에러가 발생했는지 데이터를 화면으로 보내주는 기능
      redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
      // /todo/modify?tno=글번호를 리다이렉트 할때 글번호를 설정하기 위한 기능
      redirectAttributes.addAttribute("tno", todoDTO.getTno());
      return "redirect:/todo/modify";
    }
    todoService.modify(todoDTO);

    return "redirect:/todo/read?tno="+todoDTO.getTno();
  }
}










