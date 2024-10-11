package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class SampleController {
  // WebServlet의 uri설정을 클래스 단위가 아닌 메서드 단위로 설정 가능
  // GetMapping(경로) : 경로에 맞는 get요청을 처리하는 메서드
  // Controller에서 void를 사용하는 메서드의 특징
  // GetMapping(), PostMapping()에 있는 경로로 페이지를 찾아서 실행합니다
  @GetMapping({"/hello","/hello2"})
  public void hello(){
    log.info("hello get method..................");
  }
  @PostMapping("/hello")
  public void postHello(){
    log.info("hello post method..................");
  }

}
