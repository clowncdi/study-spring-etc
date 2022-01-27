package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import stream.model.User;
import stream.service.EmailService;

public class ForEachByStream {
	public static void main(String[] args) {
		// User user1 = User.builder(101, "Alice")
		// 		.withVerified(true)
		// 		.withEmailAddress("alice@gmail.com")
		// 		.withFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214))
		// 		.build();
		// User user2 = User.builder(102, "Bob")
		// 		.withVerified(false)
		// 		.withEmailAddress("bob@gmail.com")
		// 		.withFriendUserIds(Arrays.asList(204, 205, 206))
		// 		.build();
		// User user3 = User.builder(103, "Charlie")
		// 		.withVerified(false)
		// 		.withEmailAddress("Charlie@gmail.com")
		// 		.withFriendUserIds(Arrays.asList(204, 205, 207, 218))
		// 		.build();
		User user1 = User.builder(101, "Alice")
				.with(builder -> {
					builder.isVerified = true;
					builder.emailAddress = "alice@gamil.com";
					builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
				}).build();
		User user2 = User.builder(102, "Bob")
				.with(builder -> {
					builder.isVerified = true;
					builder.emailAddress = "Bob@gamil.com";
					builder.friendUserIds = Arrays.asList(204, 205, 206);
				}).build();
		User user3 = User.builder(103, "Charlie")
				.with(builder -> {
					builder.isVerified = true;
					builder.emailAddress = "Charlie@gamil.com";
					builder.friendUserIds = Arrays.asList(204, 205, 207, 218);
				}).build();
		List<User> users = Arrays.asList(user1, user2, user3);

		EmailService emailService = new EmailService();
		users.stream()
				.filter(user -> !user.isVerified())
				.forEach(emailService::sendVerifyYourEmailEmail);
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			System.out.println(user.getName()+ " at index " +i);
		}
		IntStream.range(0, users.size()).forEach(i -> {
			User user = users.get(i);
			System.out.println(user.getName()+ " at index " +i);
		});
	}


}
