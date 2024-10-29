package org.zerock.webmarket.program.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.webmarket.program.domain.ProgramVO;
import org.zerock.webmarket.program.dto.ProgramDTO;
import org.zerock.webmarket.program.mapper.ProgramMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
  private final ProgramMapper programMapper;
  private final ModelMapper modelMapper;

  @Override
  public List<ProgramDTO> getProgramList() {
    List<ProgramVO> voList = programMapper.selectProgramList();
    List<ProgramDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo,ProgramDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }
}









