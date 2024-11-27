package org.zerock.b01.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.b01.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  @EntityGraph(attributePaths = "roleSet")
  @Query("SELECT m FROM Member m WHERE m.mid = :mid AND m.social = false")
  Optional<Member> getWithRoles(String mid);

  @EntityGraph(attributePaths = "roleSet")
  Optional<Member> findByEmail(String email);

  // INSERT, UPDATE, DELETE 문을 사용할때 붙이는 어노테이션
  @Modifying
  @Transactional
  @Query("UPDATE Member m SET m.mpw = :mpw WHERE m.mid = :mid")
  // @Param("쿼리문에 들어가는 데이터 이름") 자료형 변수이름
  // @Param의 경우 매개변수가 여러개라면 반드시 설정해야함.
  void updatePassword(@Param("mpw") String mpw, @Param("mid") String mid);
}
