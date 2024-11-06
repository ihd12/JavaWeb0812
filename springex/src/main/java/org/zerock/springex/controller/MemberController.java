package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.springex.dto.MemberDTO;
import org.zerock.springex.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/login")
  public void login(){ }
  @PostMapping("/login")
  public String login(MemberDTO dto, HttpSession session){
    try{
      MemberDTO loginInfo = memberService.login(dto);
      session.setAttribute("loginInfo", loginInfo);
      return "redirect:/todo/list";
    }catch(Exception e){
      e.printStackTrace();
      return "/member/login?error=error";
    }
  }
  @GetMapping("/join")
  public void join(){}
  @PostMapping("/join")
  public String join(MemberDTO dto){
    memberService.register(dto);
    return "redirect:/member/login";
  }

  @PostMapping("/remove")
  public String remove(HttpSession session){
    MemberDTO dto = (MemberDTO)session.getAttribute("loginInfo");
    memberService.remove(dto.getId());
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/todo/list";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session){
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/member/login";
  }
}












