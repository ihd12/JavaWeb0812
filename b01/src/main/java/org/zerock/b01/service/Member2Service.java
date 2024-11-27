package org.zerock.b01.service;

import org.zerock.b01.dto.MemberDTO;

public interface Member2Service {
  MemberDTO login(MemberDTO dto);
  void join(MemberDTO dto);
  void remove(MemberDTO dto);
}
