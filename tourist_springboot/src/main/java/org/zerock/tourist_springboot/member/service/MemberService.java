package org.zerock.tourist_springboot.member.service;

import org.zerock.tourist_springboot.member.dto.MemberDTO;

public interface MemberService {
  MemberDTO getMember(MemberDTO dto) throws Exception;
  void joinMember(MemberDTO dto);
}
