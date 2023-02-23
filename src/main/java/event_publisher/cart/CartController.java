package event_publisher.cart;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

	private final CartRepository cartRepository;

	@GetMapping
	public List<Cart> getCarts() {
		return cartRepository.findAll();
	}
}
