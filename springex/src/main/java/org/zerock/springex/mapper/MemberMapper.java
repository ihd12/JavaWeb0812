package org.zerock.springex.mapper;

import org.zerock.springex.domain.MemberVO;

public interface MemberMapper {
  void insert(MemberVO vo);
  MemberVO selectMember(MemberVO vo);
  void delete(String id);
}
