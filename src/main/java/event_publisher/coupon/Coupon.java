package event_publisher.coupon;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
