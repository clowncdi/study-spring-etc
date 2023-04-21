package proxy.config.v6_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV5Order {

	@Aspect
	@Order(2)  // order 는 클래스 단위에만 적용된다.
	public static class LogAspect {
		@Around("proxy.config.v6_aop.Pointcuts.allOrder()")
		public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[log] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}

	@Aspect
	@Order(1)
	public static class TxAspect {
		@Around("proxy.config.v6_aop.Pointcuts.orderAndService()") //&&, ||, ! 가능
		public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
			try {
				log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
				Object result = joinPoint.proceed();
				log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
				return result;
			} catch (Exception e) {
				log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
				throw e;
			} finally {
				log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
			}
		}
	}


}
