package event_publisher.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "product_amount", nullable = false)
	private BigDecimal productAmount;

	@Embedded
	private Orderer orderer;

	public Order(Long productId, BigDecimal productAmount, Orderer orderer) {
		this.productId = productId;
		this.productAmount = productAmount;
		this.orderer = orderer;
	}
}
