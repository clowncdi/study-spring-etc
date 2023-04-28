package proxy.app.aop.exam.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;
import proxy.app.aop.exam.annotation.Retry;

@Aspect
@Slf4j
public class RetryAspect {

	// Around를 쓰는 이유는 재시도 할 때 언제 joinPoint의 proceed를 해야 할지 다시 결정해야 하기 때문.
	@Around("@annotation(retry)")
	public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
		log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

		int maxRetry = retry.value();
		Exception exceptionHolder = null;

		for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
			try {
				log.info("[retry] try count={}/{}", retryCount, maxRetry);
				return joinPoint.proceed();
			} catch (Exception e) {
				exceptionHolder = e;
			}
		}
		throw exceptionHolder;
	}
}
