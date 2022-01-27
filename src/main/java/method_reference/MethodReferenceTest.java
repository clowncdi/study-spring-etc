package method_reference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceTest {
	public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
		return operator.apply(x, y);
	}

	public static int multiply(int x, int y) {
		return x * y;
	}

	public int subtract(int x, int y) {
		return x-y;
	}

	public void myMethod() {
		System.out.println(calculate(10, 3, this::subtract));
	}

	public static void main(String[] args) {

		Function<String, Integer> strToint = Integer::parseInt;
		System.out.println(strToint.apply("20"));

		String str = "hello";
		boolean b = str.equals("world");
		Predicate<String> equalsToHello = str::equals;
		System.out.println(equalsToHello.test("hello"));
		System.out.println(equalsToHello.test("world"));

		System.out.println(calculate(8, 2, MethodReferenceTest::multiply));

		MethodReferenceTest instance = new MethodReferenceTest();
		System.out.println(calculate(8, 2, instance::subtract));
		instance.myMethod();
	}
}
