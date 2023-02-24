package junit5_test.test_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Junit5_1 {

	/**
	 * junit5의 기본 전략은 테스트 코드간의 디펜던시를 줄이기 위해
	 * 테스트 코드가 실행될떄 마다, 매번 새롭게 인스턴스가 생성된다.
	 * 만약 테스트 코드간의 디펜던시가 필요하다면,
	 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) 를 사용하면 된다.
	 */
	private int count = 0;

	@Test
	void count_add_1() {
		count = count + 1;
		System.out.println("count: " + count);
		System.out.println("count_add_1: " + this);
	}

	@Test
	void count_add_2() {
		count = count + 1;
		System.out.println("count: " + count);
		System.out.println("count_add_2: " + this);
	}
}
