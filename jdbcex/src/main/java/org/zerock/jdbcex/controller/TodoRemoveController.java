package org.zerock.jdbcex.controller;

import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoRemoveController", value="/todo/remove")
public class TodoRemoveController extends HttpServlet {
  private TodoService todoService = TodoService.INSTANCE;
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      todoService.remove(Long.parseLong(req.getParameter("tno")));
    }catch(Exception e){
      e.printStackTrace();
    }
    resp.sendRedirect("/todo/list");
  }
}










