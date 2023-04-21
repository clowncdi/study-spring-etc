package proxy.config.v6_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV2 {

	//proxy.app.v4.order 패키지와 하위 패키지
	@Pointcut("execution(* proxy.app.v4.order..*(..))")  //pointcut expression
	private void allOrder(){} // pointcut signature

	@Around("allOrder()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("[log] {}", joinPoint.getSignature());
		return joinPoint.proceed();
	}

}
