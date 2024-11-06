package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.MemberVO;
import org.zerock.springex.dto.MemberDTO;
import org.zerock.springex.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberMapper memberMapper;
  private final ModelMapper modelMapper;

  @Override
  public MemberDTO login(MemberDTO dto) {
    MemberVO vo = modelMapper.map(dto, MemberVO.class);
    MemberVO result = memberMapper.selectMember(vo);
    return modelMapper.map(result, MemberDTO.class);
  }

  @Override
  public void register(MemberDTO dto) {
    MemberVO vo = modelMapper.map(dto, MemberVO.class);
    memberMapper.insert(vo);
  }

  @Override
  public void remove(String id) {
    memberMapper.delete(id);
  }
}
