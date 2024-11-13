package org.zerock.b01.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {
  @ExceptionHandler
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
    log.error(e);
    // 결과로 반환할 Map 선언 (JSON데이터를 반환하기 때문에 구조가 동일한 Map을 이용)
    Map<String, String> errorMap = new HashMap<>();
    // 에러가 있으면 if문 실행
    if(e.hasErrors()){
      BindingResult bindingResult = e.getBindingResult();
      // 반복문을 이용하여 Map에 error명과 error코드를 저장
      bindingResult.getFieldErrors().forEach(fieldError -> {
        errorMap.put(fieldError.getField(), fieldError.getCode());
      });
    }
    // 에러가 발생 했을때의 status코드와 에러 내용이 저장되어있는 Map데이터를 함께 반환
    return ResponseEntity.badRequest().body(errorMap);
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String,String>> handleFKException(Exception e){
    log.error(e);
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("time", ""+System.currentTimeMillis());
    errorMap.put("msg","constraint fails");
    return ResponseEntity.badRequest().body(errorMap);
  }
  @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  public ResponseEntity<Map<String,String>> handleNoSuchException(Exception e){
    log.error(e);
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("time", ""+System.currentTimeMillis());
    errorMap.put("msg","No Such Element Exception");
    return ResponseEntity.badRequest().body(errorMap);
  }

}













