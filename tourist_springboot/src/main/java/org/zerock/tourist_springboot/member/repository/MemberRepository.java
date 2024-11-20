package org.zerock.tourist_springboot.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.tourist_springboot.member.domain.Member;

import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

}
