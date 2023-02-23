package oop;

import java.time.LocalDate;

public class FirstOrderCouponLegacy {

	/**
	 * 안티 패턴
	 *
	 * 1. 객체간의 협력관계에서는 상대 객체에 대한 정보를 꼬치꼬치 묻지 않아야 한다. 묻지 말고 시켜라.
	 */
	public void apply(final long couponId) {
		if (canIssued()) {

			final CouponLegacy coupon = getCoupon(couponId);

			if (LocalDate.now().isAfter(coupon.getExpirationDate())) {
				throw new IllegalArgumentException("쿠폰이 만료되었습니다.");
			}

			if (coupon.isUsed()) {
				throw new IllegalArgumentException("이미 사용된 쿠폰입니다.");
			}

			coupon.setUsed(false);
		}

	}

	private CouponLegacy getCoupon(long id) {
		return new CouponLegacy(1000, LocalDate.now().plusDays(3));
	}

	private boolean canIssued() {
		// 첫 구매인지 확인하는 로직
		return true;
	}

}
