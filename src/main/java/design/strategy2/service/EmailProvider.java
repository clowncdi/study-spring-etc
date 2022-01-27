package design.strategy2.service;

import design.builder.User;

public interface EmailProvider {
	String getEmail(User user);
}
