package junit5_test.test_4;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import junit5_test.Coupon;

import static org.assertj.core.api.BDDAssertions.*;

public class CouponTest {

	@DisplayName("쿠폰 생성")
	@Test
	void test1() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		then(coupon.isUsed()).isFalse();
		then(coupon.getAmount()).isEqualTo(amount);
		then(coupon.isExpiration()).isFalse();
	}

	@DisplayName("쿠폰 할인 적용")
	@Test
	void test2() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		coupon.apply();
		then(coupon.isUsed()).isTrue();
	}

	@DisplayName("쿠폰 할인 적용시 이미 사용했을 경우")
	@Test
	void test3() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		coupon.apply();
		thenThrownBy(() -> coupon.apply())
			.isInstanceOf(IllegalStateException.class)
			.hasMessage("이미 사용한 쿠폰입니다.");
	}

	@DisplayName("쿠폰 할인 적용시 쿠폰 기간 만료했을 경우")
	@Test
	void test4() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, -10);

		thenThrownBy(() -> coupon.apply())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("사용 기간이 만료된 쿠폰입니다.");
	}

	private Coupon buildCoupon(double amount, int daysToAdd) {
		return new Coupon(amount, LocalDate.now().plusDays(daysToAdd));
	}
}
