package event_publisher;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import event_publisher.cart.Cart;
import event_publisher.cart.CartRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

	private final CartRepository cartRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		cartRepository.save(new Cart(1L));
		cartRepository.save(new Cart(2L));
		cartRepository.save(new Cart(3L));
		cartRepository.save(new Cart(4L));
		cartRepository.save(new Cart(5L));
		cartRepository.save(new Cart(6L));
	}
}
