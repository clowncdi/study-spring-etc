package error_response.order;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@OrderSheetForm
public class OrderSheetRequest {

	@Min(1)
	private BigDecimal price;

	@NotNull
	@Valid
	private Payment payment;

	@NotNull
	@Valid
	private Address address;

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	@ToString
	public static class Payment {

		@NotNull
		private PaymentMethod paymentMethod;
		private Account account;
		private Card card;

		@JsonIgnore
		public boolean hasPaymentInfo() {
			return account != null && card != null;
		}

	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	@ToString
	public static class Address {
		@NotEmpty
		private String city;
		@NotEmpty
		private String state;
		@NotEmpty
		private String zipCode;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	@ToString
	public static class Account {
		private String number;
		private String bankCode;
		private String holder;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	@ToString
	public static class Card {
		private String number;
		private String brand;
		private String cvc;
	}
}
