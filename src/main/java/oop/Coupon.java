package oop;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

	private long id;
	private boolean used;
	private double amount;
	private LocalDate expirationDate;

	public Coupon(double amount, LocalDate expirationDate) {
		this.used = false;
		this.amount = amount;
		this.expirationDate = expirationDate;
	}


	// 사용여부
	public void apply() {
		verifyCouponIsAvailable();
		this.used = true;
	}

	private void verifyCouponIsAvailable() {
		verifyExpiration();
		verifyUsed();
	}

	// 만료여부
	private boolean isExpiration() {
		return LocalDate.now().isAfter(expirationDate);
	}

	private void verifyUsed() {
		if (isUsed()) {
			throw new IllegalArgumentException("이미 사용된 쿠폰입니다.");
		}
	}

	private void verifyExpiration() {
		if (isExpiration()) {
			throw new IllegalArgumentException("쿠폰이 만료되었습니다.");
		}
	}
}
