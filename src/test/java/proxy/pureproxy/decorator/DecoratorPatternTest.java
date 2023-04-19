package proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import proxy.pureproxy.decorator.code.Component;
import proxy.pureproxy.decorator.code.DecoratorPatternClient;
import proxy.pureproxy.decorator.code.MessageDecorator;
import proxy.pureproxy.decorator.code.RealComponent;
import proxy.pureproxy.decorator.code.TimeDecorator;

/**
 * 데코레이터 패턴 : 원래 서버가 제공하는 기능에 더해서 부가 기능을 수행한다.
 * 예) 요청 값이나, 응답 값을 중간에 변형한다.
 * 예) 실행 시간을 측정해서 추가 로그를 남긴다.
 */
@Slf4j
public class DecoratorPatternTest {

	@Test
	void noDecorator() {
		Component realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}

	@Test
	void decorator1() {
		Component realComponent = new RealComponent();
		MessageDecorator messageDecorator = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
		client.execute();
	}

	@Test
	void decorator2() {
		Component realComponent = new RealComponent();
		MessageDecorator messageDecorator = new MessageDecorator(realComponent);
		TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
		client.execute();
	}
}
