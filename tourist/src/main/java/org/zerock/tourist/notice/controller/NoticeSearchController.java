package org.zerock.tourist.notice.controller;

import org.zerock.tourist.notice.dto.PageRequestDTO;
import org.zerock.tourist.notice.dto.PageResponseDTO;
import org.zerock.tourist.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice/list")
public class NoticeSearchController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PageRequestDTO pageRequestDTO = new PageRequestDTO();
    if(req.getParameter("page") != null) {
      pageRequestDTO.setPage(Integer.parseInt(req.getParameter("page")));
    }
    if(req.getParameter("size") != null) {
      pageRequestDTO.setSize(Integer.parseInt(req.getParameter("size")));
    }
    if(req.getParameter("keyword") != null) {
      pageRequestDTO.setKeyword(req.getParameter("keyword"));
    }

    try {
      PageResponseDTO pageResponseDTO = NoticeService.INSTANCE.searchNoticeList(pageRequestDTO);
      req.setAttribute("pageResponseDTO", pageResponseDTO);
      req.getRequestDispatcher("/WEB-INF/notice/notice_list.jsp").forward(req, resp);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
