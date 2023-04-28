package proxy.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.internalcall.CallServiceV2;
import proxy.app.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {

	@Autowired
	CallServiceV2 callServiceV2;

	// 지연 조회 하는 방법으로 프록시 적용.
	// applicationContext에서 빈을 찾아와서 호출하는 방식보다 ObjectProvider를 사용
	// 하지만, 결국 가장 좋은 방법은 내부 호출이 발생하지 않도록 구조를 변경하는 것이다.
	@Test
	void external() {
		callServiceV2.external();
	}



}
