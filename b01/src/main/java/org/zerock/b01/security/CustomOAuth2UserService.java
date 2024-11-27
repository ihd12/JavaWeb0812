package org.zerock.b01.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;
import org.zerock.b01.repository.MemberRepository;
import org.zerock.b01.security.dto.MemberSecurityDTO;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("userRequest..........");
    log.info(userRequest);
    log.info("oauth2 user.............");
    // 소셜 로그인의 종류를 확인하는 코드
    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    // 카카오 로그인의 경우 kakao가 저장
    String clientName = clientRegistration.getRegistrationId();
    log.info("NAME: " + clientName);
    // 로그인한 사용자 정보를 저장
    OAuth2User oAuth2User = super.loadUser(userRequest);
    // 사용자 정보를 Map으로 저장
    Map<String, Object> paramMap = oAuth2User.getAttributes();
    String nickName = null;
    switch (clientName) {
      case "kakao":
        nickName = getKakaoNickName(paramMap);
        break;
    }
    log.info("=========================");
    log.info(nickName);
    log.info("=========================");
    //사용자 정보를 반환
    return generateDTO(nickName, paramMap);
  }

  private MemberSecurityDTO generateDTO(String nickName, Map<String, Object> params) {
    // 소셜 로그인한 계정이 존재하는지 확인
    Optional<Member> result = memberRepository.findByEmail(nickName);
    if (result.isEmpty()) {
      // DB에 계정이 없을때의 실행 코드
      Member member = Member.builder()
          .mid(nickName)
          .mpw(passwordEncoder.encode("1111"))
          .email(nickName)
          .social(true)
          .build();
      member.addRole(MemberRole.USER);
      memberRepository.save(member);
      MemberSecurityDTO memberSecurityDTO =
          new MemberSecurityDTO(nickName,"1111",nickName,false,true,
              Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
      memberSecurityDTO.setProps(params);
      return memberSecurityDTO;
    } else {
      // 이미 계정이 존재할 경우 DB에 있는 계정 데이터를 반환
      Member member = result.get();
      MemberSecurityDTO memberSecurityDTO =
          new MemberSecurityDTO(
              member.getMid(),
              member.getMpw(),
              member.getEmail(),
              member.isDel(),
              member.isSocial(),
              member.getRoleSet().stream()
                  .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                  .collect(Collectors.toList())
          );
      return memberSecurityDTO;
    }
  }

  private String getKakaoNickName(Map<String, Object> paramMap) {
    log.info("KAKAO------------------------------------");
    Object value = paramMap.get("properties");
    log.info(value);
    LinkedHashMap accountMap = (LinkedHashMap) value;
    String nickName = (String) accountMap.get("nickname");
    log.info("nickname: " + nickName);
    return nickName;
  }
}
