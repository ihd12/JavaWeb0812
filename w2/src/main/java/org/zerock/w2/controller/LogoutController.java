package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("logout doGet ............");
    // removeAttribute(세션에 저장한 키값) : 세션에 저장된 데이터를 삭제하는 메서드
    req.getSession().removeAttribute("loginInfo");
    // invalidate() : 모든 세션 데이터를 삭제하는 메서드
    req.getSession().invalidate();
    //index.jsp 를 실행 (메인페이지 실행)
    resp.sendRedirect("/");
  }
}
