package proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import proxy.config.AppV1Config;
import proxy.config.AppV2Config;

/**
 * 프록시 패턴 : 접근 제어가 목적
 * 데코레이터 패턴 : 새로운 기능 추가가 목적
 */
// @Import(AppV1Config.class)
@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "proxy.app")
public class ProxyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
}
