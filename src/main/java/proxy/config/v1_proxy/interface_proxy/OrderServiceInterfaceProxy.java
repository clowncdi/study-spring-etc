package proxy.config.v1_proxy.interface_proxy;

import log.trace.TraceStatus;
import log.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import proxy.app.v1.OrderServiceV1;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1{

	private final OrderServiceV1 target;
	private final LogTrace logTrace;

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderService.orderItem()");
			//target 호출
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
