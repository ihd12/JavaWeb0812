package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String exceptNumber(NumberFormatException numberFormatException){
    log.error("---------------------------");
    log.error(numberFormatException);
    return "NUMBER FORMAT EXCEPTION";
  }

  @ResponseBody
  @ExceptionHandler
  public String exceptCommon(Exception exception){
    log.error("---------------------------");
    log.error(exception.getMessage());
    // String보다 데이터 추가가 빠른 StringBuffer를 사용 : error의 stackTrace의 경우 줄 수가 굉장히 많기 때문에
    StringBuffer buffer = new StringBuffer("<ul>");
    // 첫번째 줄은 에러 메시지 출력
    buffer.append("<li>" + exception.getMessage() + "</li>");
    // 두번째 줄 부터 stackTrace를 출력
    Arrays.stream(
        exception.getStackTrace()).forEach(
            stackTraceElement -> {buffer.append("<li>" + stackTraceElement+"</li>");}
    );
    // stackTrace출력이 끝나면 ul종료태그를 설정
    buffer.append("</ul>");
    // 만들어진 에러 문구를 화면으로 전송
    return buffer.toString();
  }
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFound(){
    return "custom404";
  }
}
