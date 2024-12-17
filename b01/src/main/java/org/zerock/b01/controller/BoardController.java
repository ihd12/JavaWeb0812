package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.*;
import org.zerock.b01.security.dto.MemberSecurityDTO;
import org.zerock.b01.service.BoardService;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
  @Value("${org.zerock.upload.path}")
  private String uploadPath;

  private final BoardService boardService;
  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model){
//    PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
    PageResponseDTO<BoardListAllDTO> pageResponseDTO = boardService.listWithAll(pageRequestDTO);
    model.addAttribute("responseDTO", pageResponseDTO);
  }
  // 로그인한 사용자에게 ROLE_USER권한이 있을때 실행 가능
//  @PreAuthorize("isAuthenticated()") 권한에 상관 없이 로그인한 유저는 모두 허용
//  @PreAuthorize("permitAll()") 모두 허용
//  @PreAuthorize("isAnonymous()") 로그인하지 않은 사용자도 허용
  @PreAuthorize("hasRole('USER')") //USER권한이 있는 사용자를 허용
//  @PreAuthorize("hasRole('ADMIN')") ADMIN권한 있는 사용자를 허용
//  @PreAuthorize("hasAnyRole('USER','ADMIN')") 둘중 하나의 권한만 있으면 허용
  @GetMapping("/register")
  public String registerGet(Principal principal,HttpSession session){


    System.out.println(principal.getName());
//    if(session.getAttribute("loginInfo") == null){
//      return "redirect:/member/login";
//    }
    return "board/register";

  }
  @PostMapping("/register")
  public String registerPost(
      @Valid BoardDTO boardDTO,
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
//  권한에 상관 없이 로그인한 유저는 모두 허용
  @PreAuthorize("isAuthenticated()")
  @GetMapping({"/read","/modify"})
  public void read(Long bno,PageRequestDTO pageRequestDTO, Model model){
    BoardDTO dto = boardService.readOne(bno);
    model.addAttribute("dto",dto);
  }
//  @PreAuthorize("principal.username == #boardDTO.writer")
  @PostMapping("/modify")
  public String modify(Principal principal,PageRequestDTO pageRequestDTO,
                     @Valid BoardDTO boardDTO,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      String link = pageRequestDTO.getLink();
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      redirectAttributes.addAttribute("bno", boardDTO.getBno());
      return "redirect:/board/modify?"+link;
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) auth.getPrincipal();
    if(auth !=null
        && (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
        || memberSecurityDTO.getMid().equals(boardDTO.getWriter()))){
      boardService.modify(boardDTO);
      redirectAttributes.addFlashAttribute("result", "modified");
      redirectAttributes.addAttribute("bno", boardDTO.getBno());
      return "redirect:/board/read";
    }
    return "redirect:/board/read?error=작성자가 아닙니다.";
  }
  @PreAuthorize("principal.username == #boardDTO.writer")
  @PostMapping("/remove")
  public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
    // Board관련된 데이터베이스 데이터 삭제
    boardService.remove(boardDTO.getBno());

    //화면에서 받아온 파일 이름들을 이용해 파일을 삭제하는 if문
    List<String> fileNames = boardDTO.getFileNames();
    if(fileNames != null && fileNames.size() > 0){
      removeFiles(fileNames);
    }

    redirectAttributes.addFlashAttribute("result", "removed");
    return "redirect:/board/list";
  }
  public void removeFiles(List<String> files){
    // 파일이름이 여러개일 경우 반복문 실행
    for(String fileName : files){
      // 파일의 데이터를 저장하는 resource
      // C:\\upload\\파일이름.확장자에 존재하는 파일을 resource에 저장
      Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
      String resourceName = resource.getFilename();
      // 파일 추가 및 삭제는 에러 발생확률이 높기 때문에 반드시 예외처리 하도록 강제됨
      try{
        // 파일이 이미지 파일 인지 아닌지 확인기 위한 타입 저장
        String contentType = Files.probeContentType(resource.getFile().toPath());
        // 파일 삭제
        resource.getFile().delete();
        // 파일이 이미지라면 썸네일도 함께 삭제
        if(contentType.startsWith("image")){
          // 썸네일 파일 데이터를 변수에 저장
          File thumnailFile = new File(uploadPath + File.separator + "s_"+fileName);
          // 썸네일 파일을 삭제
          thumnailFile.delete();
        }
      }catch(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
      }
    }
  }
}













