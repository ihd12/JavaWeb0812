package org.zerock.w2.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@Log4j2
@WebListener
public class LoginListener implements HttpSessionAttributeListener {
  //세션에 데이터가 추가될때 실행되는 리스너
  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    String name = event.getName();
    Object obj = event.getValue();
    if(name.equals("loginInfo")){
      log.info("A user logined..............");
      log.info(obj);
    }
  }


}
