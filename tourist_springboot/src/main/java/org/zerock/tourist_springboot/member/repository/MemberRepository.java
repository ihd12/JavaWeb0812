package org.zerock.tourist_springboot.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.tourist_springboot.member.domain.Member;

import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  @Query("SELECT m.member_id,m.member_pw FROM Member m WHERE m.member_id = :member_id")
  Member login(String member_id);
  @Query("SELECT m FROM Member m WHERE m.member_id = :member_id")
  Member login2(String member_id);

  @Query("SELECT m.member_id,m.member_pw FROM Member m WHERE m.member_id = :member_id")
  Map<String, String> login3(String member_id);

  @Query("SELECT m.member_id,m.member_pw FROM Member m WHERE m.member_id = :member_id")
  Member login4(@Param("member_id") String member_id);
}
