package org.zerock.tourist_springboot.notice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.tourist_springboot.notice.domain.Reply;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.dto.ReplyDTO;
import org.zerock.tourist_springboot.notice.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @Override
  public ReplyDTO read(Long rno) {
    Optional<Reply> replyOptional = replyRepository.findById(rno);
    Reply reply = replyOptional.orElseThrow();
    return modelMapper.map(reply, ReplyDTO.class);
  }

  @Override
  public void modify(ReplyDTO replyDTO) {
    Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
    Reply reply = replyOptional.orElseThrow();
    reply.changeText(replyDTO.getReplyText());
    replyRepository.save(reply);
  }

  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }

  @Override
  public PageResponseDTO<ReplyDTO> getListOfBoard(Long tno, PageRequestDTO pageRequestDTO) {
    Pageable pageable = PageRequest.of(
        pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage()-1,
        pageRequestDTO.getSize(),
        Sort.by("rno").ascending());
    Page<Reply> result = replyRepository.listOfNotice(tno, pageable);
    List<ReplyDTO> dtoList = result.getContent().stream()
        .map(reply -> modelMapper.map(reply, ReplyDTO.class))
        .collect(Collectors.toList());
    return PageResponseDTO.<ReplyDTO>withAll()
        .pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total((int)result.getTotalElements())
        .build();
  }
}
