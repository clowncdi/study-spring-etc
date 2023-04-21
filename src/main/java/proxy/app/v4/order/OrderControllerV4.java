package proxy.app.v4.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderControllerV4 {

	private final OrderServiceV4 orderService;

	public OrderControllerV4(OrderServiceV4 orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/v4/request")
	public String request(String itemId) {
		orderService.orderItem(itemId);
		return "ok";
	}

	@GetMapping("/v4/no-log")
	public String noLog() {
		return null;
	}
}
