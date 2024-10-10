package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//실행순서 : 브라우저->controller->service->dao->db->view
//작성순서 : db(테이블)->vo->dao->dto->service->controller->jsp
@Log4j2
@WebServlet(name="todoListController", value="/todo/list")
public class TodoListController extends HttpServlet {
  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("todo list ...................");
    try{
      //TodoService의 listAll을 실행하여 tbl_todo의 모든 데이터를 취득
      List<TodoDTO> dtoList = todoService.listAll();
      // view에서 사용할 데이터를 저장 setAttribute("view에서 사용할 이름", 실제 데이터 변수)
      req.setAttribute("dtoList", dtoList);
      // 데이터를 출력할 페이지 설정 및 실행
      req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }catch(Exception e){
      log.error(e.getMessage());
      throw new ServletException(e);
    }
  }
}

















