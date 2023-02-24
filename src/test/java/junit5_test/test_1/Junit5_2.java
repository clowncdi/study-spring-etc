package junit5_test.test_1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Junit5_2 {

	/**
	 * @BeforeAll 테스트 실행되기 전 한 번 실행됨
	 * @BeforeEach 모든 테스트마다 실행되기 전에 실행됨
	 * @AfterEach 모든 테스트마다 실행된 후 실행됨
	 * @AfterAll 테스트 실행된 후 한 번 실행됨
	 *
	 * @BeforeAll, @AfterAll 은 static 메소드이거나, 라이프사이클이 PER_CLASS 인 경우에만 사용 가능하다.
	 * 만약 설정이 없다면 아래처럼 에러가 발생한다.
	 * org.junit.platform.commons.JUnitException: @BeforeAll method 'void junit5_test.test_1.Junit5_2.beforeAll()'
	 * must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
	 */

	@BeforeAll
	static void beforeAll() {
		System.out.println("BeforeAll : 테스트 실행되기 이전 단 한 번만 실행");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("AfterAll : 테스트 실행된 후 단 한 번만 실행");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach : 모든 테스트마다 실행되기 전에 실행됨");
	}

	@AfterEach
	void afterEach() {
		System.out.println("AfterEach : 모든 테스트마다 실행된 후 실행됨");
	}

	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}

}
