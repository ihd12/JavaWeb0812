package org.zerock.springex.service;

import org.zerock.springex.dto.MemberDTO;

public interface MemberService {
  MemberDTO login(MemberDTO dto);
  void register(MemberDTO dto);
  void remove(String id);
}
