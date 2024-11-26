package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  @EntityGraph
  @Query("SELECT m FROM Member m WHERE m.mid = :mid AND m.social = false")
  Optional<Member> getWithRoles(String mid);
}
