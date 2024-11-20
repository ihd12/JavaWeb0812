package org.zerock.tourist_springboot.notice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.tourist_springboot.notice.dto.PageRequestDTO;
import org.zerock.tourist_springboot.notice.dto.PageResponseDTO;
import org.zerock.tourist_springboot.notice.dto.ReplyDTO;
import org.zerock.tourist_springboot.notice.service.ReplyService;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  // consumes = 통신에 사용되는 형식 설정
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  // @RequestBody : RestController에서 폼 태그의 데이터를 받기위해 반드시 필요한 어노테이션
  // ResponseEntity객체 : HttpStatus,HttpHeader, HttpBody의 내용을 설정하는 객체, 컨트롤러의 정상 실행이나 데이터등의 여러가지 데이터를 설정하여 브라우저에 돌려주는 객체
  public Map<String,Long> register(
      @Valid @RequestBody ReplyDTO replyDTO,
      BindingResult bindingResult) throws BindException {
    log.info(replyDTO);
    if(bindingResult.hasErrors()){
      throw new BindException(bindingResult);
    }
    Map<String, Long> resultMap = new HashMap<>();
    Long rno = replyService.register(replyDTO);
    resultMap.put("rno", rno);
    return resultMap;
  }

  @GetMapping(value = "/list/{tno}")
  public PageResponseDTO<ReplyDTO> getList(@PathVariable("tno") Long bno, PageRequestDTO pageRequestDTO){
    PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
    return responseDTO;
  }
  @GetMapping(value = "/{rno}")
  public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){
    ReplyDTO replyDTO = replyService.read(rno);
    return replyDTO;
  }
  @DeleteMapping("/{rno}")
  public Map<String, Long> remove(@PathVariable("rno") Long rno){
    replyService.remove(rno);
    Map<String,Long> resultMap = new HashMap<>();
    resultMap.put("rno", rno);
    return resultMap;
  }
  @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
    replyDTO.setRno(rno);
    replyService.modify(replyDTO);
    Map<String,Long> resultMap = new HashMap<>();
    resultMap.put("rno", rno);
    return resultMap;
  }

}












