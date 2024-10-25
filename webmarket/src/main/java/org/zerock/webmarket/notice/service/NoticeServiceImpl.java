package org.zerock.webmarket.notice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.webmarket.notice.domain.NoticeVO;
import org.zerock.webmarket.notice.dto.NoticeDTO;
import org.zerock.webmarket.notice.dto.PageRequestDTO;
import org.zerock.webmarket.notice.dto.PageResponseDTO;
import org.zerock.webmarket.notice.mapper.NoticeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
  private final NoticeMapper noticeMapper;
  private final ModelMapper modelMapper;
  public List<NoticeDTO> getNoticeList() {
    // mapper를 이용하여 notice테이블에서 데이터를 취득
    List<NoticeVO> noticeList = noticeMapper.selectNoticeList();
    // VO객체를 화면에서 사용하는 DTO객체로 변환하여 controller로 반환
    return noticeList.stream()
        .map(vo -> modelMapper.map(vo,NoticeDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public NoticeDTO getNotice(int tno) {
    NoticeVO vo = noticeMapper.selectNotice(tno);
    return modelMapper.map(vo, NoticeDTO.class);
  }

  @Override
  public void addNotice(NoticeDTO dto) {
    NoticeVO vo = modelMapper.map(dto, NoticeVO.class);
    noticeMapper.insertNotice(vo);
  }

  @Override
  public void modifyNotice(NoticeDTO dto) {
    noticeMapper.updateNotice(modelMapper.map(dto,NoticeVO.class));
  }

  @Override
  public void removeNotice(int tno) {
    noticeMapper.deleteNotice(tno);
  }

  @Override
  public PageResponseDTO<NoticeDTO> searchNoticeList(PageRequestDTO pageRequestDTO) {
    List<NoticeVO> voList = noticeMapper.searchNoticeList(pageRequestDTO);
    List<NoticeDTO> dtoList = voList.stream()
        .map(vo->modelMapper.map(vo, NoticeDTO.class))
        .collect(Collectors.toList());
    int total = noticeMapper.selectNoticeCount(pageRequestDTO);
    return PageResponseDTO.<NoticeDTO>withAll()
        .pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total(total)
        .build();
  }
}











