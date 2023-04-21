package proxy.app.v4.order;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;

	public OrderServiceV4(OrderRepositoryV4 orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void orderItem(String itemId) {
		log.info("[orderService 실행]");
		orderRepository.save(itemId);
	}
}
