package error_response.order;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.ObjectUtils;

public class OrderSheetFormValidator implements
		ConstraintValidator<OrderSheetForm, OrderSheetRequest> {
	@Override
	public void initialize(OrderSheetForm constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(OrderSheetRequest value, ConstraintValidatorContext context) {
		int invalidCount = 0;

		if (value.getPayment().getAccount() == null && value.getPayment().getCard() == null) {
			addConstraintViolation(context, "결제 수단은 2개 중 반드시 1개를 선택하여 진행합니다.", "payment");
			invalidCount++;
		}

		if (value.getPayment().getPaymentMethod() == PaymentMethod.CARD) {
			final OrderSheetRequest.Card card = value.getPayment().getCard();

			if (card == null) {
				addConstraintViolation(context, "카드 결제는 number, brand, cvc 정보가 필수입니다.", "payment", "card");
				invalidCount++;
			} else {
				if (ObjectUtils.isEmpty(card.getNumber())) {
					addConstraintViolation(context, "카드 번호는 필수입니다.", "payment", "card", "number");
					invalidCount++;
				}
				if (ObjectUtils.isEmpty(card.getBrand())) {
					addConstraintViolation(context, "카드사명은 필수입니다.", "payment", "card", "brand");
					invalidCount++;
				}
				if (ObjectUtils.isEmpty(card.getCvc())) {
					addConstraintViolation(context, "카드 CVC 번호는 필수입니다.", "payment", "card", "cvc");
					invalidCount++;
				}
			}
		}

		if (value.getPayment().getPaymentMethod() == PaymentMethod.BANK_TRANSFER) {
			final OrderSheetRequest.Account account = value.getPayment().getAccount();

			if (account == null) {
				addConstraintViolation(context, "계좌 정보는 필수입니다.", "payment", "account");
				invalidCount++;
			} else {
				if (ObjectUtils.isEmpty(account.getNumber())) {
					addConstraintViolation(context, "계좌 번호는 필수입니다.", "payment", "account", "number");
					invalidCount++;
				}
				if (ObjectUtils.isEmpty(account.getBankCode())) {
					addConstraintViolation(context, "은행 코드는 필수입니다.", "payment", "account", "bankCode");
					invalidCount++;
				}
				if (ObjectUtils.isEmpty(account.getHolder())) {
					addConstraintViolation(context, "계좌주는 필수입니다.", "payment", "account", "holder");
					invalidCount++;
				}
			}
		}

		return invalidCount == 0;
	}

	private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage, String firstNode, String secondNode, String thirdNode) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
				.addPropertyNode(firstNode)
				.addPropertyNode(secondNode)
				.addPropertyNode(thirdNode)
				.addConstraintViolation();
	}

	private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage, String firstNode, String secondNode) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
				.addPropertyNode(firstNode)
				.addPropertyNode(secondNode)
				.addConstraintViolation();
	}

	private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage, String firstNode) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
				.addPropertyNode(firstNode)
				.addConstraintViolation();
	}
}
