package proxy;

import org.junit.jupiter.api.Test;

import proxy.code.CacheProxy;
import proxy.code.ProxyPatternClient;
import proxy.code.RealSubject;

/**
 * 프록시 패턴을 통해 캐시로 접근하도록 처리하여 속도 개선
 */
class ProxyPatternTest {

	@Test
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		client.execute();
		client.execute();
		client.execute();
	}

	@Test
	void cacheProxyTest() {
		RealSubject realSubject = new RealSubject();
		CacheProxy cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
		client.execute();
		client.execute();
		client.execute();
	}
}
