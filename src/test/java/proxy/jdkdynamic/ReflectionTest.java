package proxy.jdkdynamic;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 리플렉션을 사용하면 클래스와 메서드의 메타정보를 사용해서 애플리케이션을 동적으올 유연하게 만들 수 있다.
 * 하지만 리플렉션 기술은 런타임에 동작하기 때문에 컴파일 시점에 오류를 잡을 수 없다.
 * 따라서 리플렉션은 일반적으로 사용하면 안된다. 지금까지 프로그래밍 언어가 발당하면서 타입 정보를 기반으로 컴파일 시점에 오류를
 * 잡아준 덕분에 개발자가 편하게 살았는데, 리플렉션은 그것에 역행하는 방식이다.
 * 리플렉션은 프레임워크 개발이나 또는 매우 일반적인 공통 처리가 필요할 때 부분적으로 주의해서 사용해야 한다.
 */
@Slf4j
class ReflectionTest {

	@Test
	void reflection0() {
		Hello hello = new Hello();

		//공통 로직1 시작
		log.info("start");
		String result1 = hello.callA(); //호출하는 메서드가 다름
		log.info("result={}", result1);
		//공통 로직1 종료

		//공통 로직2 시작
		log.info("start");
		String result2 = hello.callB(); //호출하는 메서드가 다름
		log.info("result={}", result2);
		//공통 로직2 종료
	}

	@Test
	void reflection1() throws Exception {
		//클래스 정보
		Class classHello = Class.forName("proxy.jdkdynamic.ReflectionTest$Hello");

		Hello target = new Hello();
		//callA 메서드 정보
		Method methodCallA = classHello.getMethod("callA");
		Object result1 = methodCallA.invoke(target);
		log.info("result1={}", result1);

		//callB 메서드 정보
		Method methodCallB = classHello.getMethod("callB");
		Object result2 = methodCallB.invoke(target);
		log.info("result2={}", result2);
	}

	@Test
	void reflection2() throws Exception {
		//클래스 정보
		Class classHello = Class.forName("proxy.jdkdynamic.ReflectionTest$Hello");

		Hello target = new Hello();
		//callA 메서드 정보
		Method methodCallA = classHello.getMethod("callA");
		dynamicCall(methodCallA, target);

		//callB 메서드 정보
		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB, target);
	}

	private void dynamicCall(Method method, Object target) throws Exception {
		log.info("start");
		Object result = method.invoke(target);
		log.info("result={}", result);
	}

	@Slf4j
	static class Hello {
		public String callA() {
			log.info("callA");
			return "A";
		}

		public String callB() {
			log.info("callB");
			return "B";
		}
	}

}
