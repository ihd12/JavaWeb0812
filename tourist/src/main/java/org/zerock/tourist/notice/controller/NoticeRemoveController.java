package org.zerock.tourist.notice.controller;

import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/remove")
public class NoticeRemoveController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      NoticeService.INSTANCE.removeNotice(Integer.parseInt(req.getParameter("tno")));
      resp.sendRedirect("/notice/list");
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
