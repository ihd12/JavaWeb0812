package org.zerock.tourist.notice.service;

import org.modelmapper.ModelMapper;
import org.zerock.tourist.notice.dao.NoticeDAO;
import org.zerock.tourist.notice.domain.NoticeVO;
import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.notice.dto.PageRequestDTO;
import org.zerock.tourist.notice.dto.PageResponseDTO;
import org.zerock.tourist.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

public enum NoticeService {
  INSTANCE;
  private NoticeDAO dao;
  private ModelMapper modelMapper;
  NoticeService(){
    dao = new NoticeDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }
  public List<NoticeDTO> getNoticeList() throws Exception{
    List<NoticeVO> voList = dao.selectNoticeList();
    List<NoticeDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo, NoticeDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }
  public NoticeDTO getNotice(int tno) throws Exception{
    NoticeVO vo = dao.selectNotice(tno);
    return modelMapper.map(vo, NoticeDTO.class);
  }
  public void addNotice(NoticeDTO dto) throws Exception{
    NoticeVO vo = modelMapper.map(dto, NoticeVO.class);
    dao.insertNotice(vo);
  }
  public void removeNotice(int tno) throws Exception{
    dao.deleteNotice(tno);
  }
  public void modifyNotice(NoticeDTO dto) throws Exception{
    NoticeVO vo = modelMapper.map(dto, NoticeVO.class);
    dao.updateNotice(vo);
  }
  public PageResponseDTO<NoticeDTO> searchNoticeList(PageRequestDTO pageRequestDTO) throws Exception{
    List<NoticeVO> voList = dao.searchNoticeList(pageRequestDTO);
    List<NoticeDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo, NoticeDTO.class))
        .collect(Collectors.toList());
    PageResponseDTO<NoticeDTO> pageResponseDTO = PageResponseDTO.<NoticeDTO>withAll()
        .pageRequestDTO(pageRequestDTO)
        .dtoList(dtoList)
        .total(dao.selectNoticeCount(pageRequestDTO))
        .build();
    return pageResponseDTO;
  }

}










