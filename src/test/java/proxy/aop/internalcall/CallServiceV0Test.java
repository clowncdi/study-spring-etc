package proxy.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.internalcall.CallServiceV0;
import proxy.app.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

	@Autowired
	CallServiceV0 callServiceV0;

	// 스프링은 프록시 방식의 AOP를 사용한다. 프록시 방식의 AOP는 메서드 내부 호출에 프록시를 적용할 수 없다.
	// 이를 해결하려면, 1.자기 자신을 주입하거나 2.지연 조회하거나 3.구조 변경 방법이 대안이 될 수 있다.
	@Test
	void external() {
		callServiceV0.external();
	}

	@Test
	void internal() {
		callServiceV0.internal();
	}


}
