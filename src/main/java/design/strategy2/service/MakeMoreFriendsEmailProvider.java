package design.strategy2.service;

import design.builder.User;

public class MakeMoreFriendsEmailProvider implements EmailProvider{
	@Override
	public String getEmail(User user) {
		return "'Make More Friends' email for " + user.getName();
	}
}
