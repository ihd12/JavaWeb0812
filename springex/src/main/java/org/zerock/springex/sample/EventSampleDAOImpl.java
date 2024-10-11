package org.zerock.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
// 어떤 클래스를 의존성 주입에 실행할지 설정하는 어노테이션
//@Primary
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO{
}
