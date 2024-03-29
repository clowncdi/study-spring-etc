package oop;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponLegacy {

	private long id;
	private boolean used;
	private double amount;
	private LocalDate expirationDate;

	public CouponLegacy(double amount, LocalDate expirationDate) {
		this.used = false;
		this.amount = amount;
		this.expirationDate = expirationDate;
	}
}
