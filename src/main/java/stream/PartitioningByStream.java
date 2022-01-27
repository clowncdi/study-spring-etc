package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import stream.model.User;
import stream.service.EmailService;

public class PartitioningByStream {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
		Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
				.collect(Collectors.partitioningBy(number -> number % 2 == 0));
		System.out.println("Even number: " + numberPartitions.get(true));
		System.out.println("Odd number: " + numberPartitions.get(false));

		User user1 = User.builder(101, "Alice")
				.withVerified(true)
				.withEmailAddress("alice@gmail.com")
				.withFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214))
				.build();
		User user2 = User.builder(102, "Bob")
				.withVerified(false)
				.withEmailAddress("bob@gmail.com")
				.withFriendUserIds(Arrays.asList(204, 205, 206))
				.build();
		User user3 = User.builder(103, "Charlie")
				.withVerified(false)
				.withEmailAddress("Charlie@gmail.com")
				.withFriendUserIds(Arrays.asList(204, 205, 207, 218))
				.build();
		List<User> users = Arrays.asList(user1, user2, user3);

		Map<Boolean, List<User>> userPartitions = users.stream()
				.collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));

		EmailService emailService = new EmailService();

		for (User user : userPartitions.get(true)) {
			emailService.sendPlayWithFriendsEmail(user);
		}
		for (User user : userPartitions.get(false)) {
			emailService.sendMakeMoreFriendsEmail(user);
		}


	}
}
