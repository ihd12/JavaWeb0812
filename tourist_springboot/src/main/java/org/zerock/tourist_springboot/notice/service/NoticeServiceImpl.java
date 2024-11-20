package org.zerock.tourist_springboot.notice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;
import org.zerock.tourist_springboot.notice.dto.NoticeListAllDTO;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.repository.NoticeRepository;
import org.zerock.tourist_springboot.notice.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{
  private final NoticeRepository noticeRepository;
  private final ReplyRepository replyRepository;
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
  public PageResponseDTO<NoticeListAllDTO> searchListAll(PageRequestDTO pageRequestDTO) {
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("tno");

    Page<NoticeListAllDTO> result = noticeRepository.searchReplyAndImage(keyword,pageable);

    return PageResponseDTO.<NoticeListAllDTO>withAll().
            pageRequestDTO(pageRequestDTO)
            .dtoList(result.getContent())
            .total((int)result.getTotalElements())
            .build();
  }
  @Override
  public NoticeDTO getNotice(Long tno) {
    Optional<Notice> result = noticeRepository.findById(tno);
    Notice notice = result.orElseThrow();
    return entityToDTO(notice);
  }

  @Override
  public Long registerNotice(NoticeDTO noticeDTO) {
    Notice notice = dtoToEntity(noticeDTO);
    return noticeRepository.save(notice).getTno();
  }

  @Override
  public Long modifyNotice(NoticeDTO noticeDTO) {
    Optional<Notice> result = noticeRepository.findById(noticeDTO.getTno());
    //데이터를 취득한 결과가 없으면 에러를 발생시킨다
    Notice notice = result.orElseThrow();
    notice.change(noticeDTO);
    // 이미 존재하는 이미지 파일을 모두 삭제
    notice.clearImages();
    // 새로운 이미지 파일을 추가하는 if문
    if(noticeDTO.getFileNames() != null){
      for(String fileName : noticeDTO.getFileNames()){
        // uuid와 fileName을 나누기 위한 split메서드 실행
        String[] arr = fileName.split("_",2);
        notice.addImage(arr[0], arr[1]);
      }
    }
    // 저장한 데이터의 tno를 반환
    return noticeRepository.save(notice).getTno();
  }

  @Override
  public void removeNotice(Long tno) {
    replyRepository.deleteByNotice_tno(tno);
    noticeRepository.deleteById(tno);
  }

}









