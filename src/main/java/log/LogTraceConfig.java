package log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import log.trace.logtrace.LogTrace;
import log.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
