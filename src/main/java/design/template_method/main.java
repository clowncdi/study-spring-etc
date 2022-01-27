package design.template_method;

import java.util.Arrays;

import design.builder.User;
import design.template_method.service.InternalUserService;
import design.template_method.service.UserService;

public class main {
	public static void main(String[] args) {
		User alice = User.builder(101, "Alice")
				.with((builder) -> {
					// builder.emailAddress = "Alice@gmail.com";
					builder.isVerified = false;
					builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
				}).build();

		UserService userService = new UserService();
		InternalUserService internalUserService = new InternalUserService();

		userService.createUser(alice);
		internalUserService.createUser(alice);
	}
}
