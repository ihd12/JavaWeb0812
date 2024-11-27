package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.MemberDTO;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.service.Member2Service;
import org.zerock.b01.service.MemberService;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/join")
  public void joinGET() {
    log.info("join get ...");
  }
  @PostMapping("/join")
  public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
    log.info("join post ...");
    log.info(memberJoinDTO);
    try{
      memberService.join(memberJoinDTO);
    }catch(MemberService.MidExistException e){
      // mid가 이미 존재한다면 회원가입 페이지로 리다이렉트 실행
      redirectAttributes.addFlashAttribute("error","mid");
      return "redirect:/member/join";
    }
    // mid가 없다면 데이터베이스에 데이터 저장 후 게시글 목록 페이지로 리다이렉트 실행
    redirectAttributes.addFlashAttribute("result","success");
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

//  @PostMapping("/remove")
//  public String remove(HttpSession session){
//    MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginInfo");
//    memberService.remove(loginInfo);
//    session.removeAttribute("loginInfo");
//    session.invalidate();
//    return "redirect:/board/list";
//  }

}
