package org.zerock.tourist_springboot.program.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.tourist_springboot.program.dto.ProgramDTO;
import org.zerock.tourist_springboot.program.service.ProgramService;

@Controller
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    @GetMapping("/program")
    public String getList(Model model){
        model.addAttribute("dtoList", programService.getProgramList());
        return "program/program";
    }
    @GetMapping("/program/add")
    public String add(){
        return "program/add";
    }
    @PostMapping("/program/add")
    public String add(ProgramDTO dto){
        programService.addProgram(dto);
        return "redirect:/program";
    }
}
