package log.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 * 스프링에서는 이와 같은 방식의 전략 패턴을 템플릿 콜백 패턴이라고 한다. 전략 패턴에서 'Context'가 템플릿 역할을 하고,
 * 'Strategy' 부분이 콜백으로 넘어온다 생각하면 된다. 전략 패턴에서 템플릿과 콜백 부분이 강조된 패턴이라 생각하면 된다.
 */
@Slf4j
public class ContextV2 {

	public void execute(Strategy strategy) {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		strategy.call();
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
}
