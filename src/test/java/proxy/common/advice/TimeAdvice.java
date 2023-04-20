package proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();

		// 프록시 팩토리로 프록시를 생성하는 단계에서 이미 target 정보를 파라미터로 전달받는다.
		Object result = invocation.proceed();

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("TimeProxy 종료 resultTime={}ms", resultTime);
		return result;
	}
}
