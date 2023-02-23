package event_publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 비동기 처리를 위한 어노테이션
public class EventPublisherApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventPublisherApplication.class, args);
	}
}
