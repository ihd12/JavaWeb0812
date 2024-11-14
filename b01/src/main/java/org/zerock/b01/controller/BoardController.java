package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;
  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model){
//    PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
    PageResponseDTO<BoardListReplyCountDTO> pageResponseDTO = boardService.listWithReplyCount(pageRequestDTO);
    model.addAttribute("responseDTO", pageResponseDTO);
  }
  @GetMapping("/register")
  public String registerGet(HttpSession session){
    if(session.getAttribute("loginInfo") == null){
      return "redirect:/member/login";
    }
    return "board/register";

  }
  @PostMapping("/register")
  public String registerPost(@Valid BoardDTO boardDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      log.info("has errors...............");
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      return "redirect:/board/register";
    }
    log.info(boardDTO);
    Long bno = boardService.register(boardDTO);
    redirectAttributes.addFlashAttribute("result", bno);
    return "redirect:/board/list";
  }
  @GetMapping({"/read","/modify"})
  public void read(Long bno,PageRequestDTO pageRequestDTO, Model model){
    BoardDTO dto = boardService.readOne(bno);
    model.addAttribute("dto",dto);
  }
  @PostMapping("/modify")
  public String modify(PageRequestDTO pageRequestDTO,
                     @Valid BoardDTO boardDTO,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      String link = pageRequestDTO.getLink();
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      redirectAttributes.addAttribute("bno", boardDTO.getBno());
      return "redirect:/board/modify?"+link;
    }
    boardService.modify(boardDTO);
    redirectAttributes.addFlashAttribute("result", "modified");
    redirectAttributes.addAttribute("bno", boardDTO.getBno());
    return "redirect:/board/read";
  }
  @PostMapping("/remove")
  public String remove(Long bno, RedirectAttributes redirectAttributes){
    boardService.remove(bno);
    redirectAttributes.addFlashAttribute("result", "removed");
    return "redirect:/board/list";
  }
}












