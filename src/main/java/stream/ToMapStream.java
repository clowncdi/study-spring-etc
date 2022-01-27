package stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import stream.model.Order;
import stream.model.User;

import static stream.model.Order.*;

public class ToMapStream {
	public static void main(String[] args) {
		Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
				.collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));  //x -> x == Function.identity() 같은 의미
		System.out.println(numberMap);
		System.out.println(numberMap.get(3));

		User user1 = User.builder(101, "Alice")
				.withVerified(true)
				.withEmailAddress("alice@gmail.com")
				.build();
		User user2 = User.builder(102, "Bob")
				.withVerified(false)
				.withEmailAddress("bob@gmail.com")
				.build();
		User user3 = User.builder(103, "Charlie")
				.withVerified(false)
				.withEmailAddress("Charlie@gmail.com")
				.build();
		List<User> users = Arrays.asList(user1, user2, user3);
		Map<Integer, User> userIdToUserMap = users.stream()
				.collect(Collectors.toMap(User::getId, Function.identity()));
		System.out.println(userIdToUserMap);
		System.out.println(userIdToUserMap.get(103));

		Order order1 = new Order()
				.setId(1001L)
				.setAmount(BigDecimal.valueOf(2000))
				.setStatus(OrderStatus.CREATED);
		Order order2 = new Order()
				.setId(1002L)
				.setAmount(BigDecimal.valueOf(4000))
				.setStatus(OrderStatus.ERROR);
		Order order3 = new Order()
				.setId(1003L)
				.setAmount(BigDecimal.valueOf(3000))
				.setStatus(OrderStatus.ERROR);
		Order order4 = new Order()
				.setId(1004L)
				.setAmount(BigDecimal.valueOf(7000))
				.setStatus(OrderStatus.PROCESSED);
		List<Order> orders = Arrays.asList(order1, order2, order3, order4);
		Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
				.collect(Collectors.toMap(Order::getId, Order::getStatus));
		System.out.println(orderIdToOrderStatusMap);
		System.out.println(orderIdToOrderStatusMap.get(1003L));
	}
}
