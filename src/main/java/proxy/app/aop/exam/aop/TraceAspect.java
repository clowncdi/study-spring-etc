package proxy.app.aop.exam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class TraceAspect {

	@Before("@annotation(proxy.app.aop.exam.annotation.Trace)")
	public void doTrace(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		log.info("[trace] {} args={}", joinPoint.getSignature(), args);
	}
}
