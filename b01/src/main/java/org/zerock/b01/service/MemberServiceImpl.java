package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.dto.MemberDTO;
import org.zerock.b01.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;
  private final ModelMapper modelMapper;

  @Override
  public MemberDTO login(MemberDTO dto) {
    Member result = memberRepository.findById(dto.getId()).orElseThrow();
    return modelMapper.map(result, MemberDTO.class);
  }

  @Override
  public void join(MemberDTO dto) {
    Member member = modelMapper.map(dto, Member.class);
    memberRepository.save(member);
  }

  @Override
  public void remove(MemberDTO dto) {
    memberRepository.deleteById(dto.getId());
  }
}
