package org.zerock.b01.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {
  private PasswordEncoder passwordEncoder;
  public CustomUserDetailsService() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  // 아이디와 패스워드를 확인하고 로그인 성공시 필요한 데이터를 저장하는 메서드
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername : " + username);

    // user1에 1111패스워드만 접속할 수 있도록 설정
    UserDetails userDetails = User.builder()
        .username("user1")
        .password(passwordEncoder.encode("1111"))
        .authorities("ROLE_USER")
        .build();

    //UserDetails에 유저정보, 권한정보 등의 데이터가 저장되는 객체
    return userDetails;
  }
}







