package proxy.aop.poincut;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import lombok.extern.slf4j.Slf4j;
import proxy.config.v6_aop.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class ExecutionTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void printMethod() {
		log.info("helloMethod={}", helloMethod);
	}

	/**
	 * execution 문법
	 * execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
	 * 메소드 실행 조인 포인트를 매칭한다. ?는 생략할 수 있다. '*'은 같은 패턴을 지정할 수 있다.
	 */
	@Test
	@DisplayName("가장 정밀하게 매칭한 포인트컷")
	void exactMeath() {
		//public java.lang.String proxy.config.v6_aop.member.MemberServiceImpl.hello(java.lang.String)
		//접근제어자?: public
		//반환타임: String
		//선언타입?: proxy.config.v6_aop.member.MemberServiceImpl
		//메서드이름: hello
		//파라미터: (String)
		//예외?: 생략
		pointcut.setExpression("execution(public String proxy.config.v6_aop.member.MemberServiceImpl.hello(String))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("가장 많이 생략한 포인트컷, 필수조건: 반환타입, 메서드이름, 파라미터")
	void allMatch() {
		//'*'은 아무 값이나 상관없다는 뜻
		//파라미터에서 '..'은 파라미터 타입과 파라미터 수가 상관없다는 뜻
		pointcut.setExpression("execution(* *(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("이름으로 매칭")
	void nameMatch() {
		pointcut.setExpression("execution(* hello(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("이름 패턴 매칭1")
	void nameMatchStar1() {
		pointcut.setExpression("execution(* hel*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("이름 패턴 매칭2")
	void nameMatchStar2() {
		pointcut.setExpression("execution(* *el*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("매칭 실패")
	void nameMatchStarFalse() {
		pointcut.setExpression("execution(* nomatch(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("패키지 정밀 매칭1")
	void packageExactMatch1() {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.MemberServiceImpl.hello(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("패키지 정밀 매칭2")
	void packageExactMatch2() {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.*.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("패키지 매칭 실패")
	void packageExactFalse() {
		pointcut.setExpression("execution(* proxy.config.*.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("하위 패키지 매칭1, '..' 은 해당 위치의 패키지와 하위 패키지도 포함")
	void packageMatchSubPackage1() {
		pointcut.setExpression("execution(* proxy.config.v6_aop..*.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("하위 패키지 매칭2")
	void packageMatchSubPackage2() {
		pointcut.setExpression("execution(* proxy.config..*.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타입 매칭")
	void typeExactMatch() {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.MemberServiceImpl.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타입 매칭(인터페이스)")
	void typeMatchSuperType() {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.MemberService.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타입 매칭(인터페이스), 단, 부모타입에 있는 메서드만 매칭 가능")
	void typeMatchInternal() throws NoSuchMethodException {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.MemberServiceImpl.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타입 매칭(인터페이스), 단, 부모타입에 있는 메서드만 매칭 가능")
	void typeMatchNoSuperTypeMathodFalse() throws NoSuchMethodException {
		pointcut.setExpression("execution(* proxy.config.v6_aop.member.MemberService.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse(); //자식 클래스에만 있는 internal 메서드 매칭 실패
	}

	@Test
	@DisplayName("String 타입의 파라미터 허용")
	void argMatch() {
		pointcut.setExpression("execution(* *(String))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("파라미터가 정확히 하나만 있어야 함. 모든 타입 허용(Xxx)")
	void argMatchStar() {
		pointcut.setExpression("execution(* *(*))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("갯수와 무관하게 모든 파라미터, 모든 타입 허용")
	void argMatchAll() {
		pointcut.setExpression("execution(* *(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용")
	void argMatchComplex() {
		pointcut.setExpression("execution(* *(String, ..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

}
