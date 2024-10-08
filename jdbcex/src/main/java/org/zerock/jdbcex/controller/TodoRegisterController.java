package org.zerock.jdbcex.controller;


import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet(name="todoRegisterController", value="/todo/register")
public class TodoRegisterController extends HttpServlet {
  private TodoService todoService = TodoService.INSTANCE;

  // register.jsp파일을 부르기위한 doGet메서드
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register Get..............................");
    req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req,resp);
  }
  //화면에서 보내주는 todo데이터를 저장하는 메서드
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 화면에서 가져온 데이터를 TodoDTO에 저장
    TodoDTO todoDTO = TodoDTO.builder()
        .title(req.getParameter("title"))
        .dueDate(LocalDate.parse(req.getParameter("dueDate")))
        .build();
    log.info("/todo/register Post..............................");
    log.info(todoDTO);
    // Controller에서 예외처리(try catch)를 하는 이유 : 모든 예외를 한곳에서 처리하기 위해
    // 데이터베이스 접속의 경우 반드시 예외처리가 필요함
    try{
      todoService.register(todoDTO);
    }catch(Exception e){
      e.printStackTrace();
    }
    // 추가한 데이터를 확인하기 위한 list 페이지 출력
    resp.sendRedirect("/todo/list");
  }
}










