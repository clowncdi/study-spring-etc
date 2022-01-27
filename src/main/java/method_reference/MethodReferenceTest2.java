package method_reference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.logging.Level;


import logger.LoggingTest;
import method_reference.model.User;

public class MethodReferenceTest2 {
	public static void main(String[] args) {
		Function<String, Integer> strLength = String::length;
		Integer length = strLength.apply("hello world");
		System.out.println(length);

		BiPredicate<String, String> strEquals = String::equals;
		boolean helloEqualsWorld = strEquals.test("hello", "world");
		System.out.println(helloEqualsWorld);
		// LoggingTest.logger.log(Level.WARNING, String.valueOf(helloEqualsWorld));
		System.out.println(strEquals.test("hello", "hello"));

		List<User> users = new ArrayList<>();
		users.add(new User(3, "Alice"));
		users.add(new User(1, "Charlie"));
		users.add(new User(5, "Bob"));

		printUserFilds(users, User::getId);
		users.forEach(o -> System.out.println(o.getName()));
	}

	public static void printUserFilds(List<User> users, Function<User, Object> getter) {
		for (User user : users) {
			System.out.println(getter.apply(user));
		}
	}
}
