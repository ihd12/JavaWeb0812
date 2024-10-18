package org.zerock.tourist.notice.controller;

import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/read")
public class NoticeReadController extends HttpServlet {
  private NoticeService service = NoticeService.INSTANCE;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    try{
      NoticeDTO dto = service.getNotice(Integer.parseInt(req.getParameter("tno")));
      req.setAttribute("dto", dto);
      req.getRequestDispatcher("/WEB-INF/notice/notice_view.jsp").forward(req, resp);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
