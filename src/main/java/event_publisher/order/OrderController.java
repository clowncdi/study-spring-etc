package event_publisher.order;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final OrderRepository orderRepository;

	@PostMapping
	public void doOrder(@RequestBody OrderRequest request) {
		orderService.doOrder(request);
	}

	@GetMapping
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
}
