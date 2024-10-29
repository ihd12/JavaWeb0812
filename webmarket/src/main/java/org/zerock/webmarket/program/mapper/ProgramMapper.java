package org.zerock.webmarket.program.mapper;

import org.zerock.webmarket.program.domain.ProgramVO;

import java.util.List;

public interface ProgramMapper {
  List<ProgramVO> selectProgramList();
}
