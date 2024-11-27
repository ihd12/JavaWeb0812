package org.zerock.b01.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.b01.security.CustomUserDetailsService;
import org.zerock.b01.security.handler.Custom403Handler;
import org.zerock.b01.security.handler.CustomSocialLoginSuccessHandler;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
  private final DataSource dataSource;
  private final CustomUserDetailsService userDetailsService;

  //스프링 시큐리티 기본 설정
  // 모든 페이지에 로그인이 필요, 로그인 성공시 / 경로로 이동

  // 패스워드 암호화 방식 설정
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  //스프링 시큐리티 커스텀 설정 메서드
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("---------------------configure-----------------------");
    // form태그를 사용한 로그인
    http.formLogin().loginPage("/member/login");
    // CSRF 끄기 설정
    http.csrf().disable();
    // 자동로그인 설정
    http.rememberMe()
        .key("12345678")
        .tokenRepository(persistentTokenRepository())
        .userDetailsService(userDetailsService)
        .tokenValiditySeconds(60*60*24*30);
    // 403에러 예외처리
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    // oauth2 로그인 페이지 설정
    http.oauth2Login()
        .loginPage("/member/login")
        .successHandler(authenticationSuccessHandler());

    return http.build();
  }
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler(){
    return new CustomSocialLoginSuccessHandler(passwordEncoder());
  }

  // 정적 자원 처리 메서드(이미지, js,css,html), static폴더안의 파일들
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    log.info("----------------------web configure---------------------");
            // 정적 파일들은 스프링 시큐리티로 로그인하지 않고 사용 가능하도록 설정
    return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
    repo.setDataSource(dataSource);
    return repo;
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new Custom403Handler();
  }
}










