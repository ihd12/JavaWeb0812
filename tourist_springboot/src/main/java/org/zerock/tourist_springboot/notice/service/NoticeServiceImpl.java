package org.zerock.tourist_springboot.notice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{
  private final NoticeRepository noticeRepository;
  private final ModelMapper modelMapper;

  @Override
  public PageResponseDTO<NoticeDTO> searchList(PageRequestDTO pageRequestDTO) {
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("tno");
    Page<Notice> result = noticeRepository.searchAll(keyword, pageable);
    List<NoticeDTO> dtoList = result.getContent().stream()
        .map(notice -> modelMapper.map(notice, NoticeDTO.class))
        .collect(Collectors.toList());
    return PageResponseDTO.<NoticeDTO>withAll().
        pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total((int)result.getTotalElements())
        .build();
  }

  @Override
  public int registerNotice(NoticeDTO noticeDTO) {
    Notice notice = modelMapper.map(noticeDTO, Notice.class);
    return noticeRepository.save(notice).getTno();
  }

  @Override
  public int modifyNotice(NoticeDTO noticeDTO) {
    Optional<Notice> result = noticeRepository.findById(noticeDTO.getTno());
    //데이터를 취득한 결과가 없으면 에러를 발생시킨다
    Notice notice = result.orElseThrow();
    notice.change(noticeDTO);
    // 저장한 데이터의 tno를 반환
    return noticeRepository.save(notice).getTno();
  }

  @Override
  public void removeNotice(int tno) {
    noticeRepository.deleteById(tno);
  }

  @Override
  public NoticeDTO getNotice(int tno) {
    Optional<Notice> result = noticeRepository.findById(tno);
    Notice notice = result.orElseThrow();
    return modelMapper.map(notice, NoticeDTO.class);
  }
}









