package junit5_test.test_1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Junit5_3 {

	/**
	 * @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 를 클래스 위에 선언하고,
	 * @Order 를 사용하면 테스트 코드의 실행 순서를 지정할 수 있다.
	 * 숫자가 낮을수록 먼저 실행된다.
	 */

	@Test
	@Order(2)
	void test1() {
		System.out.println("test1");
	}

	@Test
	@Order(1)
	void test2() {
		System.out.println("test2");
	}

	@Test
	@Order(3)
	void test3() {
		System.out.println("test3");
	}
}
