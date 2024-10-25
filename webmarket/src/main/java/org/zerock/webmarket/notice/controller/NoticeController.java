package org.zerock.webmarket.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.webmarket.notice.dto.NoticeDTO;
import org.zerock.webmarket.notice.dto.PageRequestDTO;
import org.zerock.webmarket.notice.dto.PageResponseDTO;
import org.zerock.webmarket.notice.service.NoticeServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
  private final NoticeServiceImpl noticeService;

  @GetMapping("/list2")
  public void getNoticeListAll(Model model){
    List<NoticeDTO> dtoList = noticeService.getNoticeList();
    model.addAttribute("dtoList", dtoList );
  }
  @GetMapping({"/read","/modify"})
  public void getNoticeRead(Model model, int tno){
    model.addAttribute("dto", noticeService.getNotice(tno));
  }
  @GetMapping("/add")
  public void addNotice(){}

  @PostMapping("/add")
  public String addNotice(NoticeDTO dto){
    noticeService.addNotice(dto);
    return "redirect:/notice/list";
  }
  @PostMapping("/modify")
  public String modifyNotice(NoticeDTO dto){
    noticeService.modifyNotice(dto);
    return "redirect:/notice/read?tno="+dto.getTno();
  }
  @PostMapping("/remove")
  public String removeNotice(int tno){
    noticeService.removeNotice(tno);
    return "redirect:/notice/list";
  }

  @GetMapping("/list")
  public void getSearchNoticeList(Model model, PageRequestDTO pageRequestDTO){
    PageResponseDTO pageResponseDTO = noticeService.searchNoticeList(pageRequestDTO);
    model.addAttribute("pageResponseDTO", pageResponseDTO);
  }
}














