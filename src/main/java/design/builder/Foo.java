package design.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

public class Foo {
	private int id;
	private String name;
	private Email email;
	private boolean isVerified;
	private LocalDateTime createdAt;
	private List<Integer> friendUserIds;

	@Builder(builderClassName = "createFooByName", builderMethodName = "createFooByName")
	public Foo(String name) {
		this.name = name;
		this.isVerified = false;
		this.createdAt = LocalDateTime.now();
		this.friendUserIds = new ArrayList<>();
	}

	@Builder(builderClassName = "createFooByEmail", builderMethodName = "createFooByEmail")
	public Foo(Email email) {
		this.email = email;
		this.isVerified = false;
		this.createdAt = LocalDateTime.now();
		this.friendUserIds = new ArrayList<>();
	}

	static class Email {
		private String emailAddress;

		public Email(String emailAddress) {
			this.emailAddress = emailAddress;
		}
	}
}
