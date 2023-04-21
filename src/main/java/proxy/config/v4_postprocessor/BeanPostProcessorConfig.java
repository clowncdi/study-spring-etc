package proxy.config.v4_postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import log.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import proxy.config.AppV1Config;
import proxy.config.AppV2Config;
import proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

	@Bean
	public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace) {
		return new PackageLogTracePostProcessor("proxy.app", getAdvisor(logTrace));
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		//pointcut
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");

		//advice
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
