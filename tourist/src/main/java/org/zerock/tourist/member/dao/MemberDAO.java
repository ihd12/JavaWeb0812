package org.zerock.tourist.member.dao;

import lombok.Cleanup;
import org.zerock.tourist.member.domain.MemberVO;
import org.zerock.tourist.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
  public void insertMember(MemberVO vo) throws Exception{
    String sql = "INSERT INTO member(member_id, member_pw,name,phone,email1,email2,gender,agree) VALUES (?,?,?,?,?,?,?,?)";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, vo.getMember_id());
    pstmt.setString(2, vo.getMember_pw());
    pstmt.setString(3, vo.getName());
    pstmt.setString(4, vo.getPhone());
    pstmt.setString(5, vo.getEmail1());
    pstmt.setString(6, vo.getEmail2());
    pstmt.setString(7, vo.getGender());
    pstmt.setBoolean(8, vo.isAgree());
    pstmt.executeUpdate();
  }
  public MemberVO selectMember(String member_id, String member_pw) throws Exception{
    String sql = "SELECT * FROM member WHERE member_id=? AND member_pw=?";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, member_id);
    pstmt.setString(2, member_pw);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    MemberVO vo = MemberVO.builder()
        .member_id(rs.getString("member_id"))
        .member_pw(rs.getString("member_pw"))
        .email1(rs.getString("email1"))
        .email2(rs.getString("email2"))
        .name(rs.getString("name"))
        .gender(rs.getString("gender"))
        .phone(rs.getString("phone"))
        .agree(rs.getBoolean("agree"))
        .create_date(rs.getDate("create_date").toLocalDate())
        .build();
    return vo;
  }
}











