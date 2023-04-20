package proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import log.trace.logtrace.LogTrace;
import proxy.app.v1.OrderControllerV1;
import proxy.app.v1.OrderControllerV1Impl;
import proxy.app.v1.OrderRepositoryV1;
import proxy.app.v1.OrderRepositoryV1Impl;
import proxy.app.v1.OrderServiceV1;
import proxy.app.v1.OrderServiceV1Impl;
import proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;

/**
 * JDK 동적 프록시는 인터페이스가 필수이다.
 * 인터페이스 없이 클래스만 있는 경우에는 CGLIB 이라는 바이트코드를 조작하는 특별한 라이브러리를 사용해야 한다.
 */
@Configuration
public class DynamicProxyBasicConfig {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

		OrderControllerV1 proxy = (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
				new Class[] {OrderControllerV1.class},
				new LogTraceBasicHandler(orderController, logTrace));

		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

		OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
				new Class[] {OrderServiceV1.class},
				new LogTraceBasicHandler(orderService, logTrace));

		return proxy;
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
		OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

		OrderRepositoryV1 proxy = (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
				new Class[] {OrderRepositoryV1.class},
				new LogTraceBasicHandler(orderRepository, logTrace));

		return proxy;
	}

}
