package log.strategy;

import org.junit.jupiter.api.Test;

import log.strategy.code.strategy.ContextV2;
import log.strategy.code.strategy.Strategy;
import log.strategy.code.strategy.StrategyLogic1;
import log.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ContextV2Test {

	/**
	 * 전략 패턴 적용
	 * 파라미터 전달 방식
	 */
	@Test
	void strategyV1() {
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogic1());
		context.execute(new StrategyLogic2());
	}

	@Test
	void strategyV2() {
		ContextV2 context = new ContextV2();
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
	}

	@Test
	void strategyV3() {
		ContextV2 context = new ContextV2();
		context.execute(() -> log.info("비즈니스 로직1 실행"));
		context.execute(() -> log.info("비즈니스 로직2 실행"));
	}

}
