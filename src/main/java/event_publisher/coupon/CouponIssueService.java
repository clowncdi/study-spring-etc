package event_publisher.coupon;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

	private final CouponRepository couponRepository;

	@Transactional
	public void issuedSignUpCoupon(Long memberId) {
		couponRepository.save(new Coupon(BigDecimal.TEN, memberId));
		throw new RuntimeException("RuntimeException 발생");
	}
}
