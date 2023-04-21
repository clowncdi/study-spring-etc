package proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import log.trace.logtrace.LogTrace;
import log.trace.logtrace.ThreadLocalLogTrace;
import proxy.config.v6_aop.AopConfig;

/**
 * 프록시 패턴 : 접근 제어가 목적
 * 데코레이터 패턴 : 새로운 기능 추가가 목적
 */
// @Import(AppV1Config.class)
// @Import({AppV1Config.class, AppV2Config.class})
// @Import(InterfaceProxyConfig.class)
// @Import(ConcreteProxyConfig.class)
// @Import(DynamicProxyBasicConfig.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(ProxyFactoryConfigV1.class)
// @Import(ProxyFactoryConfigV2.class)
// @Import(BeanPostProcessorConfig.class)
// @Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "proxy.app")
public class ProxyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
