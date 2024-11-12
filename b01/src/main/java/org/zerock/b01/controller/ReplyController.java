package org.zerock.b01.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.ReplyDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
  // consumes = 통신에 사용되는 형식 설정
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  // @RequestBody : RestController에서 폼 태그의 데이터를 받기위해 반드시 필요한 어노테이션
  // ResponseEntity객체 : HttpStatus,HttpHeader, HttpBody의 내용을 설정하는 객체, 컨트롤러의 정상 실행이나 데이터등의 여러가지 데이터를 설정하여 브라우저에 돌려주는 객체
  public ResponseEntity<Map<String,Long>> register(
      @Valid @RequestBody ReplyDTO replyDTO,
      BindingResult bindingResult) throws BindException {
    log.info(replyDTO);
    if(bindingResult.hasErrors()){
      throw new BindException(bindingResult);
    }
    Map<String, Long> resultMap = Map.of("rno",111L);
    return ResponseEntity.ok(resultMap);
  }
}












