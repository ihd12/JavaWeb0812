package org.zerock.tourist.program.dao;

import lombok.Cleanup;
import org.zerock.tourist.program.domain.ProgramVO;
import org.zerock.tourist.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {
  public List<ProgramVO> selectProgramList() throws Exception{
    String sql = "SELECT * FROM program";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    List<ProgramVO> list = new ArrayList<ProgramVO>();
    while (rs.next()) {
      ProgramVO notice = ProgramVO.builder()
          .no(rs.getInt("no"))
          .title(rs.getString("title"))
          .text(rs.getString("text"))
          .subtext(rs.getString("subtext"))
          .schedule(rs.getString("schedule"))
          .img(rs.getString("img"))
          .create_date(rs.getDate("create_date").toLocalDate())
          .build();
      list.add(notice);
    }
    return list;
  }
}
