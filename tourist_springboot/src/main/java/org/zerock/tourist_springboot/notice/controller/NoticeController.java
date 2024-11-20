package org.zerock.tourist_springboot.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.tourist_springboot.notice.dto.NoticeDTO;
import org.zerock.tourist_springboot.notice.dto.NoticeListAllDTO;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.service.NoticeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
  private final NoticeService noticeService;
  @GetMapping("/list")
  public void getNoticeList(PageRequestDTO pageRequestDTO, Model model) {
    PageResponseDTO<NoticeListAllDTO> pageResponseDTO = noticeService.searchListAll(pageRequestDTO);
    model.addAttribute("pageResponseDTO",pageResponseDTO);
  }
  @GetMapping("/add")
  public void register(PageRequestDTO pageRequestDTO){}
  @PostMapping("/add")
  public String register(NoticeDTO dto,PageRequestDTO pageRequestDTO){
    Long tno = noticeService.registerNotice(dto);
    return "redirect:/notice/read?tno="+tno+"&"+pageRequestDTO.getLink();
  }
  @GetMapping({"/read","/modify"})
  public void modify(Long tno,PageRequestDTO pageRequestDTO, Model model){
    NoticeDTO dto = noticeService.getNotice(tno);
    model.addAttribute("dto",dto);
  }
  @PostMapping("/modify")
  public String modify(NoticeDTO dto,PageRequestDTO pageRequestDTO){
    Long tno = noticeService.modifyNotice(dto);
    return "redirect:/notice/read?tno="+tno+"&"+pageRequestDTO.getLink();
  }
  @PostMapping("/remove")
  public String remove(Long tno){
    noticeService.removeNotice(tno);
    return "redirect:/notice/list";
  }
}
