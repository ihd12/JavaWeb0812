package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;

import java.time.LocalDate;

@Log4j2
@Controller
public class SampleController {
  // WebServlet의 uri설정을 클래스 단위가 아닌 메서드 단위로 설정 가능
  // GetMapping(경로) : 경로에 맞는 get요청을 처리하는 메서드
  // Controller에서 void를 사용하는 메서드의 특징
  // GetMapping(), PostMapping()에 있는 경로로 페이지를 찾아서 실행합니다
  @GetMapping("/")
  public String index() {
    return "redirect:/todo/list";
  }
  @GetMapping({"/hello","/hello2"})
  public void hello(){
    log.info("hello get method..................");
  }
  @PostMapping("/hello")
  public void postHello(){
    log.info("hello post method..................");
  }
  @GetMapping("ex1")
  public void ex1(String name, int age){
    log.info("ex1.........");
    log.info("name = " + name + ", age = " + age);
  }
  @GetMapping("ex2")
  // 자동 파라미터 수집의 경우 파라미터가 없으면 에러를 발생시키기 때문에 디폴트 값을 사용하여 에러가 발생하지 않도록 실행
  public void ex2(@RequestParam(name="name", defaultValue="AAA") String name,
                  @RequestParam(name="age", defaultValue="20") int age){
    log.info("ex2.........");
    log.info("name = " + name + ", age = " + age);
  }

  @GetMapping("/ex3")
  public void ex3(LocalDate dueDate){
    log.info("ex3........");
    log.info("dueDate = " + dueDate);
  }

  @GetMapping("/ex4")
  public void ex4(Model model){
    log.info("ex4........");
    //model을 이용하여 화면으로 데이터를 보낼 수 있다.
    model.addAttribute("message", "Hello World");
  }
  @GetMapping("/ex4-1")
  //url실행시 가지고온 데이터는 model에 설정하지 않아도 화면에서 사용할 수 있음.
  public void ex41(TodoDTO todoDTO, Model model){
    log.info("ex4-1........");
    //controller에서 변경한 내용을 화면으로 전달 가능
    todoDTO.setWriter("ex41Writer");
    log.info(todoDTO);
  }
  @GetMapping("/ex5")
  public String ex5(RedirectAttributes redirectAttributes){
//    실행 : http://localhost:8080/ex5
//    결과 : http://localhost:8080/ex6?name=ABC
    // addAttribute() : 파리미터 설정하고 화면까지 데이터를 넘겨주는 메서드
//    redirectAttributes.addAttribute("name", "ABC");
    // addFlashAttribute() : 일회용 데이터를 보내주는 메서드
    redirectAttributes.addFlashAttribute("result", "success");
    // 리다이렉트 방법
    // 메서드 반환값을 String으로 설정한 후 return 값으로 "redirect: Mapping의 주소 설정"
    return "redirect:/ex6?name=ABC";
//    return "redirect:/ex6";
  }
  @GetMapping("/ex6")
  public void ex6(String name, Model model){
    log.info(name);
    model.addAttribute("name", name);
  }
  @GetMapping("/ex7")
  public void ex7(String p1, int p2){
    log.info("p1 = " + p1);
    log.info("p2 = " + p2);
  }

}








