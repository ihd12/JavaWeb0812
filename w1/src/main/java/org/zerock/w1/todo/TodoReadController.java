package org.zerock.w1.todo;

import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", value="/todo/read")
public class TodoReadController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("/todo/read");
    // 한개의 데이터를 취득할 수 있도록 Primary Key값을 받는다
    Long tno = Long.parseLong(req.getParameter("tno"));
    // TodoService를 이용하여 한개의 데이터를 취득
    TodoDTO dto = TodoService.INSTANCE.get(tno);
    // TodoDTO를 dto라는 이름으로 request에 저장
    req.setAttribute("dto", dto);
    // read.jsp를 실행
    req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
  }
}
