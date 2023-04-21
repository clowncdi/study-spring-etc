package proxy.config.v3_proxyfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import log.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import proxy.app.v2.OrderControllerV2;
import proxy.app.v2.OrderRepositoryV2;
import proxy.app.v2.OrderServiceV2;
import proxy.config.v3_proxyfactory.advice.LogTraceAdvice;

/**
 * 빈 후처리기 - BeanPostProcessor
 * 스프링 빈 저장소에 등록할 목적으로 생성한 객체를 빈 저장소에 등록하기 직전에 조작하고 싶다면 빈 후처리기를 사용하면 된다.
 * 객체를 조작할 수도 있고, 완전히 다른 객체로 바꿔치기 하는 것도 가능하다.
 */
@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderController);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderControllerV2 proxy = (OrderControllerV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());
		return proxy;
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderService);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderServiceV2 proxy = (OrderServiceV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
		return proxy;
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
		OrderRepositoryV2 orderRepository = new OrderRepositoryV2();

		ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderRepositoryV2 proxy = (OrderRepositoryV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());
		return proxy;
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		//pointcut
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		//advice
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
