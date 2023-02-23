package oop;

import java.time.LocalDate;

public class FirstOrderCoupon {

	/**
	 * 좋은 패턴
	 *
	 * 묻지 말고 시켜라. 쿠폰 객체의 apply() 메서드를 통해서 묻지 말고 쿠폰을 적용하고 있다.
	 */
	public void apply(final long couponId) {
		if (canIssued()) {
			final Coupon coupon = getCoupon(couponId);
			coupon.apply();
		}
	}

	private Coupon getCoupon(long id) {
		return new Coupon(1000, LocalDate.now().plusDays(3));
	}

	private boolean canIssued() {
		// 첫 구매인지 확인하는 로직
		return true;
	}

}
