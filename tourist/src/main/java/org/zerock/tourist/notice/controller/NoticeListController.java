package org.zerock.tourist.notice.controller;


import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
  private NoticeService service = NoticeService.INSTANCE;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      List<NoticeDTO> noticeList = service.getNoticeList();
      req.setAttribute("noticeList", noticeList);
      req.getRequestDispatcher("/WEB-INF/notice/notice_list.jsp").forward(req, resp);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
