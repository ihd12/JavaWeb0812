package org.zerock.tourist.member.controller;

import org.zerock.tourist.member.dto.MemberDTO;
import org.zerock.tourist.member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MemberDTO dto = MemberDTO.builder()
        .member_pw(req.getParameter("member_pw"))
        .email1(req.getParameter("email1"))
        .email2(req.getParameter("email2"))
        .name(req.getParameter("name"))
        .gender(req.getParameter("gender"))
        .phone(req.getParameter("phone"))
        .agree(req.getParameter("agree").equals("on"))
        .build();
    try{
      MemberService.INSTANCE.addMember(dto);
      resp.sendRedirect("/");
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}














