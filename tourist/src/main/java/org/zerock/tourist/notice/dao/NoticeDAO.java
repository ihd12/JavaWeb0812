package org.zerock.tourist.notice.dao;

import lombok.Cleanup;
import org.zerock.tourist.notice.domain.NoticeVO;
import org.zerock.tourist.notice.dto.PageRequestDTO;
import org.zerock.tourist.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {
  public List<NoticeVO> selectNoticeList() throws Exception {
    String sql = "SELECT * FROM notice";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    @Cleanup ResultSet rs = pstmt.executeQuery();
    List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
    while (rs.next()) {
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

  public NoticeVO selectNotice(int tno) throws Exception {
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

  public void insertNotice(NoticeVO notice) throws Exception {
    String sql = "INSERT INTO notice(title,content) VALUE (?,?)";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, notice.getTitle());
    pstmt.setString(2, notice.getContent());
    pstmt.executeUpdate();
  }

  public void deleteNotice(int tno) throws Exception {
    String sql = "DELETE FROM notice WHERE tno = ?";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, tno);
    pstmt.executeUpdate();
  }

  public void updateNotice(NoticeVO vo) throws Exception {
    String sql = "UPDATE notice SET title=?, content=?, createDate=current_timestamp() WHERE tno=?";
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, vo.getTitle());
    pstmt.setString(2, vo.getContent());
    pstmt.setInt(3, vo.getTno());
    pstmt.executeUpdate();
  }

  public List<NoticeVO> searchNoticeList(PageRequestDTO pageRequestDTO) throws Exception {
    StringBuilder sql = new StringBuilder("SELECT * FROM notice ");
    if (pageRequestDTO.getKeyword() != null && pageRequestDTO.getKeyword().length() > 0) {
//      sql.append("WHERE title LIKE '%"+pageRequestDTO.getKeyword()+"%' OR content LIKE '%"+pageRequestDTO.getKeyword()+"%'");
      sql.append("WHERE title LIKE '%"+pageRequestDTO.getKeyword()+"%'");
    }
    sql.append(" ORDER BY tno DESC LIMIT " + pageRequestDTO.getSkip() + "," + pageRequestDTO.getSize());

    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql.toString());
    @Cleanup ResultSet rs = pstmt.executeQuery();
    List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
    while (rs.next()) {
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
  public int selectNoticeCount(PageRequestDTO pageRequestDTO) throws Exception {
    StringBuilder sql = new StringBuilder("SELECT COUNT(tno) FROM notice ");
    if (pageRequestDTO.getKeyword() != null && pageRequestDTO.getKeyword().length() > 0) {
//      sql.append("WHERE title LIKE '%"+pageRequestDTO.getKeyword()+"%' OR content LIKE '%"+pageRequestDTO.getKeyword()+"%'");
      sql.append("WHERE title LIKE '%"+pageRequestDTO.getKeyword()+"%'");
    }
    @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql.toString());
    @Cleanup ResultSet rs = pstmt.executeQuery();
    rs.next();
    return rs.getInt(1);
  }
}











