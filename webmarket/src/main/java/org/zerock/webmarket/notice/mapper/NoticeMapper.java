package org.zerock.webmarket.notice.mapper;

import org.zerock.webmarket.notice.domain.NoticeVO;
import org.zerock.webmarket.notice.dto.PageRequestDTO;

import java.util.List;

public interface NoticeMapper {
  List<NoticeVO> selectNoticeList();
  List<NoticeVO> searchNoticeList(PageRequestDTO pageRequestDTO);
  int selectNoticeCount(PageRequestDTO pageRequestDTO);
  NoticeVO selectNotice(int tno);
  void insertNotice(NoticeVO vo);
  void deleteNotice(int tno);
  void updateNotice(NoticeVO vo);

}
