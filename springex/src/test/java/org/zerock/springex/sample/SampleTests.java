package org.zerock.springex.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {
  //Spring에서 관리하는 Bean을 사용하기 위한 어노테이션
  @Autowired
  private SampleService sampleService;
  @Autowired
  private DataSource dataSource;

  @Test
  public void testService1(){
    log.info(sampleService);
    Assertions.assertNotNull(sampleService);
  }
  @Test
  public void testConnection() throws Exception {
    Connection connection = dataSource.getConnection();
    log.info(connection);
    Assertions.assertNotNull(connection);
    connection.close();
  }
}








