package proxy.app.v4.order;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderRepositoryV4 {
	public void save(String itemId) {
		log.info("[orderRepository] 실행");
		//저장 로직
		if (itemId.equals("ex")) {
			throw new IllegalStateException("예외 발생");
		}
	}
}
