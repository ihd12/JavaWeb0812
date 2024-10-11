package org.zerock.springex.sample;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@ToString
@RequiredArgsConstructor
public class SampleService {
  //의존성 주입 :  SampleService에 sampleDAO의 인스턴스를 선언하지 않고 사용하는 방식
//  @Autowired
//  private SampleDAO sampleDAO;

  // 생성자 주입 방식 : 의존성 주입의 한 종류로 최근에 이 방식을 사용합니다.
  @Qualifier("normal")
//  @Qualifier("event")
  private final SampleDAO sampleDAO;

}











