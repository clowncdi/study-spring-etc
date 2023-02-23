package event_publisher.cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import event_publisher.order.Order;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	@Transactional
	public void deleteCart(Order order) {
		System.out.println("CurrentTransactionNam: " + TransactionSynchronizationManager.getCurrentTransactionName());
		cartRepository.deleteById(order.getProductId());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("RuntimeException 발생"); // 예외 발생
	}
}
