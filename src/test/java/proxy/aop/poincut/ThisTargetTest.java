package proxy.aop.poincut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.member.MemberService;

/**
 * application.properties
 * spring.aop.proxy-target-class=true  CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 *
 * 참고로 this, target 지시자는 단독으로 사용되기 보다는 파라미터 바인딩에서 주로 사용된다.
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
class ThisTargetTest {

	@Autowired
	MemberService memberService;

	@Test
	void success() {
		log.info("memberService Proxy={}", memberService.getClass());
		memberService.hello("helloA");
	}

	@Slf4j
	@Aspect
	static class ThisTargetAspect {
		//부모 타입 허용
		@Around("this(proxy.app.aop.member.MemberService)")
		public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		//부모 타입 허용
		@Around("target(proxy.app.aop.member.MemberService)")
		public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		@Around("this(proxy.app.aop.member.MemberServiceImpl)")
		public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		@Around("target(proxy.app.aop.member.MemberServiceImpl)")
		public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}

}
