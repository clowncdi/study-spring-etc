package proxy.app.aop.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/aop/request")
	public String request(String itemId) {
		orderService.orderItem(itemId);
		return "ok";
	}

	@GetMapping("/aop/no-log")
	public String noLog() {
		return null;
	}
}
