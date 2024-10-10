package org.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
// 필터를 이용하여 컨트롤러가 실행기되기 전 실행하고 싶은 메서드를 실행하는 기능
// urlPatterns={"컨트롤러의 value값"} : 실행하고 싶은 url을 사용,
// *의 의미는 모든 것을 의미함으로 /todo/* 는 /todo가 붙어있는 모든 url을 실행한다.
@WebFilter(urlPatterns={"/todo/*"})
public class LoginCheckFilter implements Filter {

  // doFilter() : 필터에서 실행할 메서드 설정
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("Login Check Filter ........");
    HttpServletRequest req = (HttpServletRequest)servletRequest;
    HttpServletResponse resp = (HttpServletResponse)servletResponse;

    HttpSession session = req.getSession();
    if(session.isNew()){
      resp.sendRedirect("/login");
      return;
    }
    if(session.getAttribute("loginInfo") ==null){
      resp.sendRedirect("/login");
      return;
    }

    // 필터를 실행하기위 반드시 필요한 부분 : servletRequest,servletResponse 를 이용하여 controller에 데이터를 보내거나 여러가지 기능을 사용할 수 있음
    filterChain.doFilter(servletRequest, servletResponse);
  }
}














