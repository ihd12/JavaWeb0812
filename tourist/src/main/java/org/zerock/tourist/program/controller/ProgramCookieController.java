package org.zerock.tourist.program.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie")
public class ProgramCookieController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String title = req.getParameter("title").replace(" ","");
      //Cookie를 이용하여 열었던 tno 저장하기
      // findCookie() 메서드는 저장되어있는 Cookie중 viewTodoCookie가 있는지 확인
      Cookie programCookie = findCookie(req.getCookies(), "programCookie");
      String programStr = programCookie.getValue();
      boolean exist = false;

      // 눌렀던 tno를 저장하는 처리
      // 쿠키의 값이 있는지 없는지 확인 && tno가 쿠키의 값 안에 존재하는지 확인 하는 if문
      // 새로운 데이터만 저장되고 이미 있는 데이터는 한번 더 저장되지 않도록 하는 부분
      if(programStr != null && programStr.indexOf(title+"-") >= 0){
        exist = true;
      }
      if(!exist){
        // 기존에 저장되었 있던 기록에 tno를 더함
        programStr += title+"-";
        // tno를 더한 데이터를 cookie에 저장(Cookie의 value값 갱신)
        programCookie.setValue(programStr);
        // Cookie의 경로 갱신
        programCookie.setPath("/");
        // Cookie의 만료시간 갱신
        programCookie.setMaxAge(60*60*24);
        // Cookie를 저장 (이미 존재하는 쿠키의 경우 쿠키를 갱신, 덮어쓰기)
        resp.addCookie(programCookie);
      }
      resp.sendRedirect("/program");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public Cookie findCookie(Cookie[] cookies, String cookieName){
    Cookie targetCookie = null;
    // cookies가 null이 아니고 데이터가 0개보다 클 때
    if(cookies != null && cookies.length > 0){
      // cookies에 들어있는 쿠키의 이름을 확인하기 위한 반복문
      for(Cookie cookie : cookies){
        // cookie의 key값이 찾는 이름과 같은지 확인하는 if문
        if(cookie.getName().equals(cookieName)){
//        key값과 찾는 이름이 같다면 targetCookie에 저장
          targetCookie = cookie;
          // 일치하는 내용을 찾았기 때문에 반복문 종료
          break;
        }
      }
    }
    // 이미 만들어진 쿠키가 없을때 새로운 쿠키를 생성하는 if문
    if(targetCookie == null){
      // cookieName으로 쿠키를 생성
      targetCookie = new Cookie(cookieName, "");
      // 경로 설정 / = localhost:8080
      targetCookie.setPath("/");
      // 만료기간 설정 : 초단위로 설정하기 떄문에 60초*60*24 = 1일
      targetCookie.setMaxAge(60*60*24);
    }
    return targetCookie;
  }
}
