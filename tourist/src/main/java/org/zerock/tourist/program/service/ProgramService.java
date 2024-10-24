package org.zerock.tourist.program.service;

import org.modelmapper.ModelMapper;
import org.zerock.tourist.notice.dao.NoticeDAO;
import org.zerock.tourist.notice.domain.NoticeVO;
import org.zerock.tourist.notice.dto.NoticeDTO;
import org.zerock.tourist.program.dao.ProgramDAO;
import org.zerock.tourist.program.domain.ProgramVO;
import org.zerock.tourist.program.dto.ProgramDTO;
import org.zerock.tourist.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

public enum ProgramService {
  INSTANCE;
  private ProgramDAO dao;
  private ModelMapper modelMapper;
  ProgramService(){
    dao = new ProgramDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }
  public List<ProgramDTO> getNoticeList() throws Exception{
    List<ProgramVO> voList = dao.selectProgramList();
    List<ProgramDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo, ProgramDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }
}
