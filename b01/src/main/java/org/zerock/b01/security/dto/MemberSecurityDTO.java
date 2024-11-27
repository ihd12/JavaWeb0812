package org.zerock.b01.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
// User클래스는 UserDetails인터페이스를 상속받아 만들었기 때문에 스프링 시큐리티의 사용자 정보를 저장 가능
public class MemberSecurityDTO extends User implements OAuth2User {
  private String mid;
  private String mpw;
  private String email;
  private boolean del;
  private boolean social;
  // 소셜 로그인 정보 변수
  private Map<String,Object> props;

  // 스프링 시큐리티에서는 사용자 ID를 username으로 사용하고 비밀번호는 password로 사용하기 때문에 mid,mpw로 변경하는 로직이 필요하고 나머지 데이터도 설정해야함.
  public MemberSecurityDTO(String username, String password, String email,
                           boolean del, boolean social,
                           Collection<? extends GrantedAuthority> authorities) {
    // UserDetails를 생성하려면 반드시 super를 실행해야함.
    super(username, password, authorities);
    this.mid = username;
    this.mpw = password;
    this.email = email;
    this.del = del;
    this.social = social;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.getProps();
  }

  @Override
  public String getName() {
    return this.mid;
  }
}
