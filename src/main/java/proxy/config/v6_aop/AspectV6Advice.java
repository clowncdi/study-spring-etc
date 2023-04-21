package proxy.config.v6_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV6Advice {

	/**
	 * 모든 어드바이스는 org.aspectj.lang.JoinPoint를 첫번째 파라미터에 사용할 수 있다.(생략해도 된다)
	 * 단, @Around는 ProceedingJoinPoint 을 사용해야 한다.(다음(타겟)을 호출해줘야 하기 때문)
	 */
	@Around("proxy.config.v6_aop.Pointcuts.orderAndService()") //&&, ||, ! 가능
	public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			//@Before
			log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
			Object result = joinPoint.proceed();
			//@AfterReturning
			log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
			return result;
		} catch (Exception e) {
			//@AfterThrowing
			log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
			throw e;
		} finally {
			//@After
			log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
		}
	}

	// @Around가 아닌 세부적인 어드바이스들은 proceed를 실행할 필요없고, 보다 명시적인 장점이 있음(좋은 제약).
	@Before("proxy.config.v6_aop.Pointcuts.orderAndService()")
	public void doBefore(JoinPoint joinPoint) {
		log.info("[before] {}", joinPoint.getSignature());
	}

	@AfterReturning(value = "proxy.config.v6_aop.Pointcuts.orderAndService()", returning = "result") //return값 이름(파라미터 바인딩용)
	public void doReturn(JoinPoint joinPoint, Object result) {
		log.info("[return] {} return={}", joinPoint.getSignature(), result); // 리턴값(result)를 편집할 순 없다.
	}

	@AfterThrowing(value = "proxy.config.v6_aop.Pointcuts.orderAndService()", throwing = "ex") //throwing값 이름(파라미터 바인딩용)
	public void doThrowing(JoinPoint joinPoint, Exception ex) {
		log.info("[ex] {} message={}", joinPoint.getSignature(), ex); // 예외값 자동 throw
	}

	@After("proxy.config.v6_aop.Pointcuts.orderAndService()")
	public void doAfter(JoinPoint joinPoint) {
		log.info("[after] {}", joinPoint.getSignature());
	}



}
