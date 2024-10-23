package org.zerock.tourist.notice.controller;

import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/modify")
public class NoticeModifyController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      NoticeDTO dto = NoticeService.INSTANCE.getNotice(Integer.parseInt(req.getParameter("tno")));
      req.setAttribute("dto", dto);
      req.getRequestDispatcher("/WEB-INF/notice/notice_modify.jsp").forward(req, resp);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      NoticeDTO dto = NoticeDTO.builder()
          .tno(Integer.parseInt(req.getParameter("tno")))
          .title(req.getParameter("title"))
          .content(req.getParameter("content"))
          .build();
      NoticeService.INSTANCE.modifyNotice(dto);
      resp.sendRedirect("/notice/read?tno="+dto.getTno());
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
