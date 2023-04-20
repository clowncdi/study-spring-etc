package proxy.advisor;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import lombok.extern.slf4j.Slf4j;
import proxy.common.advice.TimeAdvice;
import proxy.common.service.ServiceImpl;
import proxy.common.service.ServiceInterface;

/**
 * 프록시 팩토리 용어 정리
 * 포인트컷(Pointcut) : 어디에 부가 기능을 적용할지, 안 할지를 판단하는 필터링 로직이다. 주로 클래스와 메서드 이름으로 필터링한다.
 * 어드바이스(Advice) : 프록시가 호출하는 부가 기능이다. 단순하게 프록시 로직이라 생각하면 된다.
 * 어드바이저(Advisor) : 하나의 포인트컷과 하나의 어드바이스를 가지고 있는 것이다.
 */
@Slf4j
public class AdvisorTest {

	private static String matchName;

	@Test
	void advisorTest1() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

		proxy.save();
		proxy.find();
	}

	@Test
	@DisplayName("직접 만든 포인트컷")
	void advisorTest2() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

		proxy.save();
		proxy.find();
	}

	@Test
	@DisplayName("스프링이 제공하는 포인트컷")
	void advisorTest3() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("save");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

		proxy.save();
		proxy.find();
	}

	static class MyPointcut implements Pointcut {

		@Override
		public ClassFilter getClassFilter() {
			return ClassFilter.TRUE;
		}

		@Override
		public MethodMatcher getMethodMatcher() {
			return new MyMethodMatcher();
		}
	}

	static class MyMethodMatcher implements MethodMatcher {

		private String matchName = "save";

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			boolean result = method.getName().equals(matchName);
			log.info("포인트컷 호출 method={} targetClass={}", method.getName(), targetClass);
			log.info("포인트컷 결과 result={}", result);
			return result;
		}

		@Override
		public boolean isRuntime() {
			return false;
		}

		@Override
		public boolean matches(Method method, Class<?> targetClass, Object... args) {
			throw new UnsupportedOperationException();
		}
	}

}
