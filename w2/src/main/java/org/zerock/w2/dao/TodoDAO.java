package org.zerock.w2.dao;

import lombok.Cleanup;
import org.zerock.w2.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
  public String getTime() {
    String now = null;
    //try with resource문 :  Connection,PreparedStatement,ResultSet 은 close()를 실행해야하기 때문에 사용
    try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
         // PreparedStatement : SQL실행하기 위한 사전준비
         PreparedStatement pstmt = connection.prepareStatement("SELECT now()");
         // ResultSet : PreparedStatement에 설정한 SQL의 실행결과를 저장하는 객체
         ResultSet rs = pstmt.executeQuery()) {
      //iterator의 특징을 가진 객체로 next() 메서드를 이용하여 데이터를 출력가능
      rs.next(); // 첫번째 데이터로 이동
      now = rs.getString(1); // 첫번째 데이터를 취득
    } catch (Exception e) {
      e.printStackTrace();
    }
    return now;
  }

  public String getTime2() throws Exception{
    // @Cleanup : lombok에서 지원하는 어노테이션으로 close를 실행해주는 기능
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement("SELECT now()");
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    String now = rs.getString(1);
    return now;
  }
  public void insert(TodoVO vo) throws Exception {
    // ?의 의미 : 설정해야하는 데이터 부분에 ?를 설정하여 PreparedStatement에서 번호를 이용하여 ?에 설정하는 방식
    String sql = "INSERT INTO tbl_todo (title,dueDate,finished) VALUES(?,?,?)";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setString(1, vo.getTitle()); // 첫번째 ?의 데이터 설정
    pstmt.setDate(2, Date.valueOf(vo.getDueDate())); // 두번째 ?의 데이터 설정
    pstmt.setBoolean(3,vo.isFinished()); // 세번째 ?의 데이터 설정
    // executeQuery() : select문을 실행하는 메서드
    // executeUpdate() : insert, update, delete문을 실행하는 메서드
    pstmt.executeUpdate();
  }

  public List<TodoVO> selectAll() throws Exception{
    String sql = "SELECT * FROM tbl_todo";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    //반환하기 위한 List<TodoVO> 선언
    List<TodoVO> list = new ArrayList<>();
    //데이터베이스에서 취득한 데이터를 List<TodoVO> 에 담기위해 반복문 실행
    while (rs.next()) {
      // List<TodoVO>안에 담기위한 TodoVO객체 생성
      TodoVO vo = TodoVO.builder()
          .tno(rs.getLong("tno"))
          .title(rs.getString("title"))
          .dueDate(rs.getDate("dueDate").toLocalDate())
          .finished(rs.getBoolean("finished"))
          .build();
      // List<TodoVO>에 vo데이터 추가
      list.add(vo);
    }
    return list;
  }
  public TodoVO selectOne(Long tno) throws Exception {
    // 데이터 한개를 취득하는 경우 Primary Key 를 이용하여 검색하는 것이 가장 빠르다.
    String sql = "SELECT * FROM tbl_todo WHERE tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
    // ?의 데이터를 반드시 설정해야함
    pstmt.setLong(1, tno);
    // SELECT을 실행 후 결과를 ResultSet에 저장
    @Cleanup ResultSet rs = pstmt.executeQuery();
    // 첫번째 데이터를 꺼내는 기능
    rs.next();
    // 반환객체인 TodoVO를 선언 및 초기화
    TodoVO vo = TodoVO.builder()
        .tno(rs.getLong("tno"))
        .title(rs.getString("title"))
        .dueDate(rs.getDate("dueDate").toLocalDate())
        .finished(rs.getBoolean("finished"))
        .build();
    return vo;
  }
  public void delete(Long tno) throws Exception {
    // 삭제도 PK를 이용하여 실행, 원하는 행 하나만 삭제가 가능한 PK를 사용할 것
    String sql = "DELETE FROM tbl_todo WHERE tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setLong(1, tno);
    pstmt.executeUpdate();
  }

  public void update(TodoVO vo) throws Exception {
    String sql = "UPDATE tbl_todo SET title = ?,dueDate = ?, finished = ? WHERE tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
    // SQL의 ?에 들어가는 데이터 순서대로 적어야 합니다.
    pstmt.setString(1, vo.getTitle());
    pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
    pstmt.setBoolean(3, vo.isFinished());
    pstmt.setLong(4,vo.getTno());
    pstmt.executeUpdate();
  }
}
