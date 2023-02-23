package event_publisher.order;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class OrderRequest {

	private Long productId;
	private BigDecimal productAmount;
	private Orderer orderer;

	public Order toEntity() {
		return new Order(productId, productAmount, orderer);
	}

}
