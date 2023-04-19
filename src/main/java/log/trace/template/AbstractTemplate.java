package log.trace.template;

import log.trace.TraceStatus;
import log.trace.logtrace.LogTrace;

/**
 * 템플릿 메서드 패턴은 상속을 사용하기 때문에 상속에서 오는 단점들을 그대로 안고 간다.
 * 특히 자식 클래스가 부모 클래스에 강겹합되어 있다는 문제가 있다.
 * 그리고 상속 구조를 사용하기 때문에 별도의 클래스나 익명 내부 클래스를 만들어야 하는 부분도 복잡하다.
 * 템플릿 메서드 패턴과 비슷하면서 상속의 단점을 제거할 수 있는 전략 패턴이 있다. (상속보다 위임을!)
 */
public abstract class AbstractTemplate<T> {

	private final LogTrace trace;

	public AbstractTemplate(LogTrace trace) {
		this.trace = trace;
	}

	public T execute(String message) {
		TraceStatus status = null;
		try {
			status = trace.begin(message);

			//로직 호출
			T result = call();

			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

	protected abstract T call();
}
