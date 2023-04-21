package proxy.config.v6_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import log.trace.TraceStatus;
import log.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LogTraceAspect {

	private final LogTrace logTrace;

	public LogTraceAspect(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	@Around("execution(* proxy.app..*(..))") // AspectJ 표현식
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus status = null;

		log.info("target={}", joinPoint.getTarget()); //실제 호출 대상
		log.info("getArgs={}", joinPoint.getArgs()); //전달인자
		log.info("getSignature={}", joinPoint.getSignature()); //join point 시그니처

		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);
			//target 호출
			Object result = joinPoint.proceed();
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
