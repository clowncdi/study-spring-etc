package proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import log.trace.logtrace.LogTrace;
import proxy.app.v1.OrderControllerV1;
import proxy.app.v1.OrderControllerV1Impl;
import proxy.app.v1.OrderRepositoryV1;
import proxy.app.v1.OrderRepositoryV1Impl;
import proxy.app.v1.OrderServiceV1;
import proxy.app.v1.OrderServiceV1Impl;
import proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;

@Configuration
public class InterfaceProxyConfig {

	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
		return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
		return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
		OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
		return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
	}
}
