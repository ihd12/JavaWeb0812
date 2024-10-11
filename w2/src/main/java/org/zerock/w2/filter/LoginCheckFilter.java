package org.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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
    //쿠키에 UUID가 있으면 로그인이 되어야하기 때문에 JSESSIONID확인 불필요
//    if(session.isNew()){
//      resp.sendRedirect("/login");
//      return;
//    }
    // 세션에 로그인 정보가 있을 경우 다음 페이지로 진행하도록 설정하는 if문
    if(session.getAttribute("loginInfo") != null){
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    // 로그인 관련 쿠키를 검색하여 반환
    Cookie cookie = findCookie(req.getCookies(), "remember-me");
    if(cookie == null){
      resp.sendRedirect("/login");
      return;
    }
    try{
      String uuid = cookie.getValue();
      //uuid를 이용하여 데이터베이스의 Member데이터 취득
      MemberDTO dto = MemberService.INSTANCE.getByUUID(uuid);
      // 데이터베이스에 해당하는 UUID가 없을 경우 예외를 발생시키고 로그인 페이지로 이동
      if(dto == null){
        throw new Exception("Cookie value is not valid");
      }
      session.setAttribute("loginInfo", dto);
      // 필터를 실행하기위 반드시 필요한 부분 : servletRequest,servletResponse 를 이용하여 controller에 데이터를 보내거나 여러가지 기능을 사용할 수 있음
      filterChain.doFilter(servletRequest, servletResponse);

    }catch(Exception e){
      e.printStackTrace();
      resp.sendRedirect("/login");
    }

  }
  private Cookie findCookie(Cookie[] cookies, String name){
    // 쿠키가 하나도 없으면 null값을 반환 , 로그인 관련 처리이기 때문에 새로운 쿠키를 만들지 않음
    if(cookies == null || cookies.length == 0){
      return null;
    }
    Optional<Cookie> result = Arrays.stream(cookies)
        // filter(cookies의 데이터 한개 -> 조건식) : 리스트나 배열 안의 데이터 중 조건식에 맞는 데이터만 남겨줌
        .filter(cookie-> cookie.getName().equals(name))
        //필터에서 찾은 데이터 중에 첫번째 데이터를 반환
        .findFirst();
    // result가 null인지 확인하고 false라면 null을 true라면 result의 값을 반환
    return result.isPresent() ? result.get() : null ;
  }
}














