package design.strategy2.service;

import design.builder.User;

public class VerifyYourEmailAddressEmailProvider implements EmailProvider{
	@Override
	public String getEmail(User user) {
		return "'Verify Your Email Address' email for " + user.getName();
	}
}
