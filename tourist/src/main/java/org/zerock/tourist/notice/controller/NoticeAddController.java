package org.zerock.tourist.notice.controller;

import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/add")
public class NoticeAddController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/notice/notice_add.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    NoticeDTO dto = NoticeDTO.builder()
        .title(req.getParameter("title"))
        .content(req.getParameter("content"))
        .build();
    try{
      NoticeService.INSTANCE.addNotice(dto);
    }catch(Exception e){
      e.printStackTrace();
    }
    resp.sendRedirect("/notice/list");
  }
}
