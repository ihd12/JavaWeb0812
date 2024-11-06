package org.zerock.tourist_springboot.notice.service;

import org.zerock.tourist_springboot.notice.dto.NoticeDTO;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;

public interface NoticeService {
  PageResponseDTO<NoticeDTO> searchList(PageRequestDTO pageRequestDTO);
  int registerNotice(NoticeDTO noticeDTO);
  int modifyNotice(NoticeDTO noticeDTO);
  void removeNotice(int tno);
  NoticeDTO getNotice(int tno);
}
