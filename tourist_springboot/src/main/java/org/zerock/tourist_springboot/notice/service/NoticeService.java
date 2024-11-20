package org.zerock.tourist_springboot.notice.service;

import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;
import org.zerock.tourist_springboot.notice.dto.NoticeListAllDTO;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface NoticeService {
  PageResponseDTO<NoticeDTO> searchList(PageRequestDTO pageRequestDTO);
  PageResponseDTO<NoticeListAllDTO> searchListAll(PageRequestDTO pageRequestDTO);
  Long registerNotice(NoticeDTO noticeDTO);
  Long modifyNotice(NoticeDTO noticeDTO);
  void removeNotice(Long tno);
  NoticeDTO getNotice(Long tno);

  default Notice dtoToEntity(NoticeDTO dto){
    Notice notice = Notice.builder()
            .tno(dto.getTno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .view(dto.getView())
            .build();
    if(dto.getFileNames() != null){
      dto.getFileNames().forEach(fileName ->{
        String[] arr = fileName.split("_",2);
        notice.addImage(arr[0], arr[1]);
      });
    }
    return notice;
  }
  default NoticeDTO entityToDTO(Notice notice){
    NoticeDTO dto = NoticeDTO.builder()
            .tno(notice.getTno())
            .title(notice.getTitle())
            .content(notice.getContent())
            .view(notice.getView())
            .build();
    List<String> fileNames = notice.getImageSet().stream().sorted()
            .map(noticeImage -> noticeImage.getUuid()+"_"+noticeImage.getFileName())
            .collect(Collectors.toList());
    dto.setFileNames(fileNames);
    return dto;
  }
}
