package org.zerock.tourist_springboot.program.service;

import org.zerock.tourist_springboot.program.dto.ProgramDTO;

import java.util.List;

public interface ProgramService {
    List<ProgramDTO> getProgramList();
    void addProgram(ProgramDTO dto);
}
