package org.zerock.tourist_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TouristSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(TouristSpringbootApplication.class, args);
  }

}
