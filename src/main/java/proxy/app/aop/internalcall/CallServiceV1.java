package proxy.app.aop.internalcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallServiceV1 {

	private CallServiceV1 callServiceV1;

	// 자기 자신을 주입할 때 생성자 주입이 아닌, setter 주입으로 처리(생성자 주입은 내부 순환 참조 예외가 터진다)
	@Autowired
	public void setCallServiceV1(CallServiceV1 callServiceV1) {
		log.info("callServiceV1 setter={}", callServiceV1.getClass());
		this.callServiceV1 = callServiceV1;
	}

	public void external() {
		log.info("call external");
		callServiceV1.internal(); //외부 메서드 호출
	}

	public void internal() {
		log.info("call internal");
	}
}
