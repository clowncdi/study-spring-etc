package proxy.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.internalcall.CallServiceV1;
import proxy.app.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV1Test {

	@Autowired
	CallServiceV1 callServiceV1;

	// 자기 자신을 주입하는 방법으로 프록시 적용. (setter 주입 방식으로 해결)
	@Test
	void external() {
		callServiceV1.external();
	}



}
