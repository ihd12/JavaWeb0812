package org.zerock.tourist_springboot.member.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.tourist_springboot.member.domain.Member;
import org.zerock.tourist_springboot.member.dto.MemberDTO;
import org.zerock.tourist_springboot.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;
  private final ModelMapper modelMapper;
  public MemberDTO getMember(MemberDTO dto) throws Exception{
    Member member = memberRepository.findById(dto.getMember_id()).orElseThrow();
    if(member == null || !member.getMember_pw().equals(dto.getMember_pw())){
      throw new  Exception("아이디나 비밀번호가 일치하지 않습니다.");
    }
    return modelMapper.map(member, MemberDTO.class);
  }
  public void joinMember(MemberDTO dto){
    Member member = modelMapper.map(dto, Member.class);
    memberRepository.save(member);
  }
}
