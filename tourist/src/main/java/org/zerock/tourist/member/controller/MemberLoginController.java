package org.zerock.tourist.member.controller;

import org.zerock.tourist.member.dto.MemberDTO;
import org.zerock.tourist.member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      MemberDTO dto = MemberService.INSTANCE.getMember(req.getParameter("member_id"), req.getParameter("member_pw"));
      HttpSession session = req.getSession();
      session.setAttribute("loginInfo", dto);
      resp.sendRedirect("/");
    }catch(Exception e){
      e.printStackTrace();
      resp.sendRedirect("/member/login");
    }
  }
}











