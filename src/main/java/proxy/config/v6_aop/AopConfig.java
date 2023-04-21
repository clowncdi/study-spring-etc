package proxy.config.v6_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import log.trace.logtrace.LogTrace;
import proxy.config.AppV1Config;
import proxy.config.AppV2Config;
import proxy.config.v6_aop.aspect.LogTraceAspect;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

	@Bean
	public LogTraceAspect logTraceAspect(LogTrace logTrace) {
		return new LogTraceAspect(logTrace);
	}
}
