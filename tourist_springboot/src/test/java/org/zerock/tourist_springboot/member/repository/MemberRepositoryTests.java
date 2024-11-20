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

}
