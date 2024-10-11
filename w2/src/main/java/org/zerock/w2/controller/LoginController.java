package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

// value값만 설정
@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
  MemberService memberService = MemberService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("login doGet.................");
    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("login doPost.................");
    // login화면에서 보내준 아이디와 비밀번호를 취득
    String mid = req.getParameter("mid");
    String mpw = req.getParameter("mpw");
    String auto = req.getParameter("auto");
    // auto값이 null이 아니고 on일때 true값을 저장
    boolean rememberMe = auto != null && auto.equals("on");

    try{
      // 로그인 아이디와 비밀번호를 이용하여 데이터베이스에서 데이터를 취득
      MemberDTO loginInfo = memberService.login(mid,mpw);
      if(rememberMe){
        // UUID : 128비트 고유 식별자
        String uuid = UUID.randomUUID().toString();
        // uuid를 데이터베이스에 저장
        memberService.modifyUuid(mid,uuid);
        // uuid를 세션에 저장
        loginInfo.setUuid(uuid);
        // uuid를 쿠키에 저장
        Cookie rememberCookie = new Cookie("remember-me", uuid);
        rememberCookie.setPath("/");
        rememberCookie.setMaxAge(60*60*24*7);
        resp.addCookie(rememberCookie);
      }
      // 요청에 들어있는 세션 정보 취득
      HttpSession session = req.getSession();
      //세션에 로그인 처리에서 사용할 loginInfo 데이터를 저장
      session.setAttribute("loginInfo", loginInfo);
      resp.sendRedirect("/todo/list");
    }catch(Exception e){
      e.printStackTrace();
      // result 키값으로 error를 저장하여 로그인 실패했다는 것을 화면에 전달함.
      resp.sendRedirect("/login?result=error");
    }
  }
}










