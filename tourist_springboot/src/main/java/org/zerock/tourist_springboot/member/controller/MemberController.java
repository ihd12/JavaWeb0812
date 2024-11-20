package org.zerock.tourist_springboot.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.tourist_springboot.member.dto.MemberDTO;
import org.zerock.tourist_springboot.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;
  @GetMapping("/login")
  public void login() {}
  @PostMapping("/login")
  public String login(MemberDTO dto, HttpSession session) {
    try{
      MemberDTO loginInfo = memberService.getMember(dto);
      session.setAttribute("loginInfo", loginInfo);
      return "redirect:/";
    }catch(Exception e){
      e.printStackTrace();
      return "redirect:/member/login?error=error";
    }
  }
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/";
  }
  @GetMapping("/join")
  public void join() {}
  @PostMapping("/join")
  public String join(MemberDTO dto) {
    try{
      memberService.joinMember(dto);
      return "redirect:/";
    }catch(Exception e){
      e.printStackTrace();
      return "redirect:/member/join";
    }
  }
}
