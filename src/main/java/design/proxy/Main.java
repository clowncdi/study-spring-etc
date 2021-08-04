package design.proxy;

import java.util.concurrent.atomic.AtomicLong;

import design.aop.AopBrowser;

public class Main {
	public static void main(String[] args) {
		// Browser browser = new Browser("naver.com");
		// browser.show();
		// browser.show();
		// browser.show();

		// IBrowser browser = new BrowserProxy("naver.com");
		// browser.show();
		// browser.show();
		// browser.show();

		AtomicLong start = new AtomicLong();
		AtomicLong end = new AtomicLong();

		IBrowser aopBrowser = new AopBrowser("naver.com",
				()->{
					System.out.println("before");
					start.set(System.currentTimeMillis());
				},
				()->{
					long now = System.currentTimeMillis();
					end.set(now - start.get());
				}
		);

		aopBrowser.show();
		System.out.println("loading time : " + end.get());

		aopBrowser.show();
		System.out.println("loading time : " + end.get());

	}
}
