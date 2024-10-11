package org.zerock.w2.dao;

import lombok.Cleanup;
import org.zerock.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
  // mid와 mpw를 이용해 데이터베이스의 Member를 찾는 메서드
  public MemberVO getWithPassword(String mid, String mpw) throws Exception{
    String query = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? AND mpw = ?";
    MemberVO vo = null;
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1,mid);
    pstmt.setString(2,mpw);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    vo = MemberVO.builder()
        .mid(rs.getString("mid"))
        .mpw(rs.getString("mpw"))
        .mname(rs.getString("mname"))
        .build();
    return vo;
  }
  public void updateUuid(String mid , String uuid) throws Exception{
    String sql = "UPDATE tbl_member SET uuid = ? WHERE mid = ?";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,uuid);
    pstmt.setString(2,mid);
    pstmt.executeUpdate();
  }

  public MemberVO selectUUID(String uuid) throws Exception{
    String query = "SELECT mid, mpw, mname, uuid FROM tbl_member WHERE uuid = ?";
    MemberVO vo = null;
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1,uuid);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    vo = MemberVO.builder()
        .mid(rs.getString("mid"))
        .mpw(rs.getString("mpw"))
        .mname(rs.getString("mname"))
        .uuid(rs.getString("uuid"))
        .build();
    return vo;
  }
}







