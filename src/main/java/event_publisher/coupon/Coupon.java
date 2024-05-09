package event_publisher.coupon;

import jakarta.persistence.Column;
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
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "member_id", nullable = false, updatable = false)
	private Long memberId;

	public Coupon(BigDecimal amount, Long memberId) {
		this.amount = amount;
		this.memberId = memberId;
	}
}
