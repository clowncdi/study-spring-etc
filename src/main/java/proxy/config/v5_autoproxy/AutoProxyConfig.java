package proxy.config.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import log.trace.logtrace.LogTrace;
import proxy.config.AppV1Config;
import proxy.config.AppV2Config;
import proxy.config.v3_proxyfactory.advice.LogTraceAdvice;

/**
 * 자동 프록시 생성기를 이용하기 위해서는 아래 aop 의존성 추가 필요함
 * implementation 'org.springframework.boot:spring-boot-starter-aop'
 */
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

	// @Bean
	public Advisor advisor1(LogTrace logTrace) {
		//pointcut
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		//advice
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}

	@Bean
	public Advisor advisor2(LogTrace logTrace) {
		//pointcut
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* proxy.app..*(..)) && !execution(* proxy.app..noLog(..))");
		//advice
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
