package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {
  @Override
  public Boolean parse(String text, Locale locale) throws ParseException {
    // text가 null일 경우 false 반환
    if(text == null){
      return false;
    }
    // text의 데이터가 on이면 true를 on이 아니면 false를 반환
    return text.equals("on");
  }

  @Override
  public String print(Boolean object, Locale locale) {
    return "";
  }
}
