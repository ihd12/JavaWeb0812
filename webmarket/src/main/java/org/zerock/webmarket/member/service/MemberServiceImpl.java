package org.zerock.webmarket.member.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.webmarket.member.domain.MemberVO;
import org.zerock.webmarket.member.dto.MemberDTO;
import org.zerock.webmarket.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberMapper memberMapper;
  private final ModelMapper modelMapper;

  @Override
  public void addMember(MemberDTO dto) {
    MemberVO vo = modelMapper.map(dto, MemberVO.class);
    memberMapper.insertMember(vo);
  }

  @Override
  public MemberDTO login(MemberDTO dto) {
    MemberVO vo = modelMapper.map(dto, MemberVO.class);
    MemberVO resultVO = memberMapper.login(vo);
    MemberDTO resultDTO = modelMapper.map(resultVO, MemberDTO.class);
    return resultDTO;
  }
}
