package org.zerock.tourist_springboot.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.tourist_springboot.notice.domain.Notice;
import org.zerock.tourist_springboot.program.domain.Program;

public interface ProgramRepository extends JpaRepository<Program,Long> {
}
