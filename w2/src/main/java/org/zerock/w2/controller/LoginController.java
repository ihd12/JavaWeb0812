package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// value값만 설정
@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
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
    // 로그인 아이디와 비밀번호를 저장하는 임시 데이터
    String loginInfo = mid + mpw;
    // 요청에 들어있는 세션 정보 취득
    HttpSession session = req.getSession();
    //세션에 로그인 처리에서 사용할 loginInfo 데이터를 저장
    session.setAttribute("loginInfo", loginInfo);
    String info = session.getAttribute("loginInfo").toString();

    resp.sendRedirect("/todo/list");

  }
}










