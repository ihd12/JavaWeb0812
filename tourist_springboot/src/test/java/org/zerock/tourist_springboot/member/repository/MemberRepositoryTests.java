package org.zerock.tourist_springboot.member.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.tourist_springboot.member.domain.Member;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository memberRepository;
  @Test
  public void getLogin1(){
    Member member = memberRepository.login("1234");
    System.out.println(member);
  }
  @Test
  public void getLogin2(){
    Member member = memberRepository.login2("1234");
    System.out.println(member);
  }
  @Test
  public void getLogin3(){
    Map<String,String> member = memberRepository.login3("1234");
    System.out.println(member);
  }
  @Test
  public void getLogin4(){
    Member member = memberRepository.login4("1234");
    System.out.println(member);
  }
  @Test
  public void getMemberFindById(){
    Optional<Member> member = memberRepository.findById("1234");
    System.out.println(member);
  }
}
