package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
  private final ReplyRepository replyRepository;
  private final ModelMapper modelMapper;
  @Override
  public Long register(ReplyDTO replyDTO) {
    Reply reply = modelMapper.map(replyDTO,Reply.class);
    Long rno = replyRepository.save(reply).getRno();
    return rno;
  }
}
