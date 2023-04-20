package proxy.config.v1_proxy.concrete_proxy;

import log.trace.TraceStatus;
import log.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import proxy.app.v2.OrderRepositoryV2;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

	private final OrderRepositoryV2 target;
	private final LogTrace logTrace;

	@Override
	public void save(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRepository.save()");
			//target 호출
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
