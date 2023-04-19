package log.strategy;

import org.junit.jupiter.api.Test;

import log.strategy.code.strategy.ContextV1;
import log.strategy.code.strategy.Strategy;
import log.strategy.code.strategy.StrategyLogic1;
import log.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴은 변하지 않는 부분을 'Context'라는 곳에 두고, 변하는 부분을 'Strategy'라는 인터페이스를 만들고 해당 인터페이스를
 * 구현하도록 해서 문제를 해결한다. 상속이 아니라 위임으로 문제를 해결하는 것이다.
 * 전략 패턴에서 'Context'는 변하지 않는 템플릿 역할을 하고, 'Strategy'는 변하는 알고리즘 역할을 한다.
 * 선조립 후 실행 방식은 유연성이 떨어지므로 전략을 실행할 때 직접 파라미터로 전달해서 사용할 수 있다.
 */
@Slf4j
class ContextV1Test {

	@Test
	void templateMethodV0() {
		logic1();
		logic2();
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직1 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직2 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	/**
	 * 전략 패턴 사용
	 */
	@Test
	void strategyV1() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}

	@Test
	void strategyV2() {
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();

		Strategy strategyLogic2 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}

	@Test
	void strategyV3() {
		ContextV1 context1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		context1.execute();

		ContextV1 context2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
		context2.execute();
	}

	@Test
	void strategyV4() {
		ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		context1.execute();

		ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		context2.execute();
	}
}
