package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member2;
import org.zerock.b01.dto.MemberDTO;
import org.zerock.b01.repository.Member2Repository;

@Service
@RequiredArgsConstructor
public class Member2ServiceImpl implements Member2Service {
  private final Member2Repository member2Repository;
  private final ModelMapper modelMapper;

  @Override
  public MemberDTO login(MemberDTO dto) {
    Member2 result = member2Repository.findById(dto.getId()).orElseThrow();
    return modelMapper.map(result, MemberDTO.class);
  }

  @Override
  public void join(MemberDTO dto) {
    Member2 member2 = modelMapper.map(dto, Member2.class);
    member2Repository.save(member2);
  }

  @Override
  public void remove(MemberDTO dto) {
    member2Repository.deleteById(dto.getId());
  }
}
