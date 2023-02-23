package event_publisher.order;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import event_publisher.cart.CartService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

	private final CartService cartService;

	@Async
	@EventListener
	public void orderCompletedEventListener(OrderCompletedEvent event) {
		cartService.deleteCart(event.getOrder());
	}
}
