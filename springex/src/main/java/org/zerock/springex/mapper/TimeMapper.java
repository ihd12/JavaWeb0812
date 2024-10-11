package org.zerock.springex.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

public interface TimeMapper {
  // 간다한 sql을 실행하는 mybatis
  //@Select("SELET SQL 작성")
  @Select("SELECT now()")
  String getTime();
}
