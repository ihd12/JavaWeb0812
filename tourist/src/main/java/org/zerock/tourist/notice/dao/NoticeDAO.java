package org.zerock.tourist.notice.dao;

import lombok.Cleanup;
import org.zerock.tourist.notice.domain.NoticeVO;
import org.zerock.tourist.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {
  public List<NoticeVO> selectNoticeList() throws Exception{
    String sql = "SELECT * FROM notice";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
    while(rs.next()){
      NoticeVO notice = NoticeVO.builder()
          .tno(rs.getInt("tno"))
          .title(rs.getString("title"))
          .content(rs.getString("content"))
          .view(rs.getInt("view"))
          .createDate(rs.getTimestamp("createDate").toLocalDateTime())
          .build();
      noticeList.add(notice);
    }
    return noticeList;
  }
  public NoticeVO selectNotice(int tno) throws Exception{
    String sql = "SELECT * FROM notice WHERE tno = ?";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, tno);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    NoticeVO vo = NoticeVO.builder()
        .tno(rs.getInt("tno"))
        .title(rs.getString("title"))
        .view(rs.getInt("view"))
        .content(rs.getString("content"))
        .createDate(rs.getTimestamp("createDate").toLocalDateTime())
        .build();
    return vo;
  }
  public void insertNotice(NoticeVO notice) throws Exception{
    String sql = "INSERT INTO notice(title,content) VALUE (?,?)";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, notice.getTitle());
    pstmt.setString(2, notice.getContent());
    pstmt.executeUpdate();
  }
}











