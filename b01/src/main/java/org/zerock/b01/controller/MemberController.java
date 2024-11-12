package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.b01.dto.MemberDTO;
import org.zerock.b01.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/join")
  public void join() {}
  @PostMapping("/join")
  public String join(MemberDTO dto){
    memberService.join(dto);
    return "redirect:/board/list";
  }

  @GetMapping("/login")
  public void login(){}
  @PostMapping("/login")
  public String login(MemberDTO dto, HttpSession session){
    try{
      MemberDTO loginInfo = memberService.login(dto);
      session.setAttribute("loginInfo", loginInfo);
      return "redirect:/board/list";
    }catch(Exception e){
      e.printStackTrace();
      return "redirect:/member/login?error=error";
    }
  }
  @GetMapping("/logout")
  public String logout(HttpSession session){
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/board/list";
  }

  @PostMapping("/remove")
  public String remove(HttpSession session){
    MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginInfo");
    memberService.remove(loginInfo);
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/board/list";
  }

}
