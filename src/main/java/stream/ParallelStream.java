package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import stream.model.User;
import stream.service.EmailService;

public class ParallelStream {
	public static void main(String[] args) {

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
		User user4 = User.builder(104, "frank")
				.withVerified(true)
				.withEmailAddress("frank@gmail.com")
				.build();
		User user5 = User.builder(105, "ieio")
				.withVerified(false)
				.withEmailAddress("ieio@gmail.com")
				.build();
		User user6 = User.builder(106, "assdf")
				.withVerified(false)
				.withEmailAddress("assdf@gmail.com")
				.build();
		List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);


		long startTime = System.currentTimeMillis();
		EmailService emailService = new EmailService();
		users.stream()
				.filter(user -> !user.isVerified())
				.forEach(emailService::sendVerifyYourEmailEmail);
		long endTime = System.currentTimeMillis();
		System.out.println("Sequential: " + (endTime - startTime) + "ms");

		startTime = System.currentTimeMillis();
		users.stream().parallel()
				.filter(user -> !user.isVerified())
				.forEach(emailService::sendVerifyYourEmailEmail);
		endTime = System.currentTimeMillis();
		System.out.println("Parallel: " + (endTime - startTime) + "ms");


		List<User> processedUsers = users.parallelStream()
				.map(user -> {
					System.out.println("Capitalize user name for user " + user.getId());
					// user.setName(user.getName().toUpperCase());
					return user;
				})
				.map(user -> {
					System.out.println("Set 'isVerified' to true for user " + user.getId());
					// user.setVerified(true);
					return user;
				})
				.collect(Collectors.toList());
		System.out.println(processedUsers);
	}
}
