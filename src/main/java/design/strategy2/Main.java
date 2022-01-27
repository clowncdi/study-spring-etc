package design.strategy2;

import java.util.Arrays;
import java.util.List;

import design.builder.User;
import design.strategy2.service.EmailProvider;
import design.strategy2.service.EmailSender;
import design.strategy2.service.MakeMoreFriendsEmailProvider;
import design.strategy2.service.VerifyYourEmailAddressEmailProvider;

public class Main {
	public static void main(String[] args) {
		User user1 = User.builder(101, "Alice")
				.with((builder) -> {
					builder.emailAddress = "Alice@gmail.com";
					builder.isVerified = false;
					builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
				}).build();
		User user2 = User.builder(102, "Bob")
				.with((builder) -> {
					builder.emailAddress = "Bob@gmail.com";
					builder.isVerified = true;
					builder.friendUserIds = Arrays.asList(212, 213, 214);
				}).build();
		User user3 = User.builder(103, "Charlie")
				.with((builder) -> {
					builder.emailAddress = "Charlie@gmail.com";
					builder.isVerified = false;
					builder.friendUserIds = Arrays.asList(201, 204, 211, 212, 213);
				}).build();
		List<User> users = Arrays.asList(user1, user2, user3);

		EmailSender emailSender = new EmailSender();
		EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
		EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

		emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
		users.stream()
				.filter(user -> !user.isVerified())
				.forEach(emailSender::sendEmail);

		emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
		users.stream()
				.filter(User::isVerified)
				.filter(user -> user.getFriendUserIds().size() <= 5)
				.forEach(emailSender::sendEmail);

		emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
		users.stream()
				.filter(User::isVerified)
				.filter(user -> user.getFriendUserIds().size() > 5)
				.forEach(emailSender::sendEmail);
	}
}
