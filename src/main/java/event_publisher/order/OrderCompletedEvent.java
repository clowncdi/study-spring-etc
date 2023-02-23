package event_publisher.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderCompletedEvent {

	private final Order order;
}
