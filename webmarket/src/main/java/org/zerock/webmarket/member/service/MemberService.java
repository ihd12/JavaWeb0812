package org.zerock.webmarket.member.service;

import org.zerock.webmarket.member.dto.MemberDTO;

public interface MemberService {
  void addMember(MemberDTO dto);
  MemberDTO login(MemberDTO dto);
}
