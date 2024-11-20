package org.zerock.tourist_springboot.notice.service;


import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.dto.ReplyDTO;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);
  ReplyDTO read(Long rno);
  void modify(ReplyDTO replyDTO);
  void remove(Long rno);
  PageResponseDTO<ReplyDTO> getListOfBoard(Long tno, PageRequestDTO pageRequestDTO);
}
