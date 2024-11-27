package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final ModelMapper modelMapper;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
    String mid = memberJoinDTO.getMid();
    // existsById메서드는 pk가 존재하는지 확인하는 메서드
    boolean exist = memberRepository.existsById(mid);
    // ID가 존재한다면 같은 ID로 가입할 수 없기 때문에 예외 발생
    if (exist) {
      throw new MidExistException();
    }
    // memberJoinDTO를 Member변환
    Member member = modelMapper.map(memberJoinDTO, Member.class);
    // 비밀번호를 입력한 그대로가 아닌 암호화를 하여 저장
    member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
    // 계정의 권한을 USER로 설정
    member.addRole(MemberRole.USER);

    log.info("===========");
    log.info(member);
    log.info(member.getRoleSet());
    // 데이터베이스 사용자 저장
    memberRepository.save(member);
  }
}











