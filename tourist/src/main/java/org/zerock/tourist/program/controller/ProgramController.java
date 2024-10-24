package org.zerock.tourist.program.controller;

import org.zerock.tourist.program.dto.ProgramDTO;
import org.zerock.tourist.program.service.ProgramService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/program")
public class ProgramController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      List<ProgramDTO> dtoList = ProgramService.INSTANCE.getNoticeList();
      req.setAttribute("dtoList", dtoList);
      req.getRequestDispatcher("/WEB-INF/program/program.jsp").forward(req, resp);
    }catch(Exception e){
      e.printStackTrace();
      resp.sendRedirect("/");
    }
  }
}
