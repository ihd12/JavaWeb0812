package org.zerock.webmarket.member.mapper;

import org.zerock.webmarket.member.domain.MemberVO;

public interface MemberMapper {
  void insertMember(MemberVO vo);
  MemberVO login(MemberVO vo);
}
