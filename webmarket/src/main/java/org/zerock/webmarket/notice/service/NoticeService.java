package org.zerock.webmarket.notice.service;

import org.zerock.webmarket.notice.dto.NoticeDTO;
import org.zerock.webmarket.notice.dto.PageRequestDTO;
import org.zerock.webmarket.notice.dto.PageResponseDTO;

import java.util.List;

public interface NoticeService {
  List<NoticeDTO> getNoticeList();
  NoticeDTO getNotice(int tno);
  void addNotice(NoticeDTO dto);
  void modifyNotice(NoticeDTO dto);
  void removeNotice(int tno);
  PageResponseDTO<NoticeDTO> searchNoticeList(PageRequestDTO pageRequestDTO);
}









