package proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import lombok.extern.slf4j.Slf4j;
import proxy.cglib.code.TimeMethodInterceptor;
import proxy.common.service.ConcreteService;

/**
 * 인터페이스 없는 구체 클래스에 동적 프록시를 적용할 때 CGLIB를 사용한다.
 */
@Slf4j
class CglibTest {

	@Test
	void cglib() {
		ConcreteService target = new ConcreteService();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreteService.class);
		enhancer.setCallback(new TimeMethodInterceptor(target));
		ConcreteService proxy = (ConcreteService) enhancer.create();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.call();
	}
}
