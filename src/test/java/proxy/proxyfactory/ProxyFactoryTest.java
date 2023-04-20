package proxy.proxyfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import lombok.extern.slf4j.Slf4j;
import proxy.common.advice.TimeAdvice;
import proxy.common.service.ConcreteService;
import proxy.common.service.ServiceImpl;
import proxy.common.service.ServiceInterface;

import static org.assertj.core.api.Assertions.*;

/**
 * 프록시 팩토리를 생성할 때 호출 대상을 함께 넘겨준다.
 * 이 인스턴스 정보를 기반으로 프록시를 만드는데, 만약 이 인스턴스가 인터페이스가 있다면,
 * JDK 동적 프록시를 기본으로 사용하고, 인터페이스가 없고 구체 클래스만 있다면 CGLIB를 통해서 동적 프록시를 생성한다.
 * 스프링 부트는 AOP를 적용할 때 기본적으로 proxyTargetClass=true 로 설정해서 사용한다.(항상 CGLIB 사용)
 */
@Slf4j
public class ProxyFactoryTest {

	@Test
	@DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
	void interfaceProxy() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		// 프록시 팩토리를 통해 만든 프록시가 사용할 부가 기능 로직을 설정한다.
		// 이렇게 부가 기능 로직을 어드바이스(Advice)라 한다.
		proxyFactory.addAdvice(new TimeAdvice());
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.save();

		assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
		assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
	}

	@Test
	@DisplayName("구체 클래스만 있으면 CGLIB 사용")
	void concreteProxy() {
		ConcreteService target = new ConcreteService();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new TimeAdvice());
		ConcreteService proxy = (ConcreteService)proxyFactory.getProxy();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.call();

		assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
		assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
	}

	@Test
	@DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
	void proxyTargetClass() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true);
		proxyFactory.addAdvice(new TimeAdvice());
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.save();

		assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
		assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
	}
}
