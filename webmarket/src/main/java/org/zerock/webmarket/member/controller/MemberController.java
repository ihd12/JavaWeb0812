package org.zerock.webmarket.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.webmarket.member.dto.MemberDTO;
import org.zerock.webmarket.member.service.MemberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
  private final MemberServiceImpl memberService;

  @GetMapping("/join")
  public void join(){}
  @PostMapping("/join")
  public String join(MemberDTO dto) {
    dto.setMember_id(dto.getEmail1());
    memberService.addMember(dto);
    return "redirect:/member/login";
  }

  @GetMapping("/login")
  public void login(){}
  @PostMapping("/login")
  public String login(MemberDTO dto, HttpServletRequest req){
    try{
      MemberDTO loginInfo = memberService.login(dto);
      HttpSession session = req.getSession();
      session.setAttribute("loginInfo", loginInfo);
      return "redirect:/";
    }catch(Exception e){
      return "redirect:/member/login?error=error";
    }
  }
  @GetMapping("/logout")
  public String logout(HttpSession session){
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/";
  }
}












