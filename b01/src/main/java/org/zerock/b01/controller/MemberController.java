package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.b01.dto.MemberDTO;
import org.zerock.b01.service.MemberService;

@Log4j2
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
  public void loginGet(String error, String logout){
    log.info("login get................");
    log.info("logout : " + logout);
    if(logout != null){
      log.info("user logout.................");
    }
  }

// 스프링 시큐리티로 login, logout처리를 실행하기 때문에 주석처리
//  @PostMapping("/login")
//  public String login(MemberDTO dto, HttpSession session){
//    try{
//      MemberDTO loginInfo = memberService.login(dto);
//      session.setAttribute("loginInfo", loginInfo);
//      return "redirect:/board/list";
//    }catch(Exception e){
//      e.printStackTrace();
//      return "redirect:/member/login?error=error";
//    }
//  }
//  @GetMapping("/logout")
//  public String logout(HttpSession session){
//    session.removeAttribute("loginInfo");
//    session.invalidate();
//    return "redirect:/board/list";
//  }

  @PostMapping("/remove")
  public String remove(HttpSession session){
    MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginInfo");
    memberService.remove(loginInfo);
    session.removeAttribute("loginInfo");
    session.invalidate();
    return "redirect:/board/list";
  }

}
