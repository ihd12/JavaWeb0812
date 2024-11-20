package org.zerock.tourist_springboot.program.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.tourist_springboot.program.domain.Program;
import org.zerock.tourist_springboot.program.dto.ProgramDTO;
import org.zerock.tourist_springboot.program.repository.ProgramRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ProgramDTO> getProgramList() {
        return programRepository.findAll().stream()
                .map(program -> modelMapper.map(program, ProgramDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addProgram(ProgramDTO dto) {
        Program program = modelMapper.map(dto, Program.class);
        programRepository.save(program);
    }
}
