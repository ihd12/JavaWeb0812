package org.zerock.b01.service;

import org.zerock.b01.dto.MemberDTO;

public interface MemberService {
  MemberDTO login(MemberDTO dto);
  void join(MemberDTO dto);
  void remove(MemberDTO dto);
}
