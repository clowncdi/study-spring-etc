package log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import log.trace.logtrace.FieldLogTrace;
import log.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

	@Bean
	public LogTrace logTrace() {
		return new FieldLogTrace();
	}

}
