package design.builder;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		User user1 = User.builder(101, "Alice")
				.withEmailAddress("alice@gmail.com")
				.withCreatedAt(LocalDateTime.now())
				.withIsVerified(true)
				.build();
		System.out.println(user1.toString());

		// with 메서드 하나로 생성
		User user2 = User.builder(102, "Bob")
				.with((builder) -> {
					builder.isVerified = true;
					builder.friendUserIds = Arrays.asList(101, 103, 104);
					builder.emailAddress = "Bob@gmail.com";
					builder.createdAt = LocalDateTime.now();
				}).build();
		System.out.println(user2.toString());
	}
}
