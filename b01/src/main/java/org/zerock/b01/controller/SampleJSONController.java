package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 화면을 돌려주지 않는 컨트롤러로 데이터만 돌려줄 수 있습니다.
@RestController
@Log4j2
public class SampleJSONController {
  @GetMapping("/helloArr")
  //restController의 경우 메서드의 반환값은 실제 돌려줄 데이터의 반환값
  public String[] helloArr(){
    log.info("helloArr");
    return new String[] {"AAA","BBB","CCC"};
  }
}
