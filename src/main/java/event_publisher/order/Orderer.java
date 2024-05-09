package event_publisher.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Orderer {

	@Column(name = "member_id", nullable = false, updatable = false)
	private Long memberId;

	@Column(name = "email", nullable = false, updatable = false)
	private String email;
}
