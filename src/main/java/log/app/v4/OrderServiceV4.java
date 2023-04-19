package log.app.v4;

import org.springframework.stereotype.Service;

import log.trace.logtrace.LogTrace;
import log.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate(trace) {
			@Override
			protected Void call() {
				orderRepository.save(itemId);
				return null;
			}
		};
		template.execute("OrderService.orderItem()");

	}
}
