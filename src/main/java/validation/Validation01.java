package validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Validation01 {

	/**
	 * Validation V1 - BindingResult 검증
	 * BindingResult는 @ModelAttribute 바로 뒤에 위치해야 한다. 순서 중요.
	 * BindingResult는 @Valid를 사용하지 않아도 사용할 수 있다.
	 */
	@PostMapping("/valid-v1")
	public String bindingResult(@ModelAttribute Item item, BindingResult bindingResult) {
		// 검증 로직
		if(!StringUtils.hasText(item.getItemName())) {
			bindingResult.addError(new FieldError("item", "name", "상품 이름은 필수입니다."));
		}
		if(item.getPrice() < 1000 || item.getPrice() > 1000000) {
			bindingResult.addError(new FieldError("item", "price", "가격은 1,000원 ~ 1,000,000원 사이입니다."));
		}
		if(item.getQuantity() < 1 || item.getQuantity() > 10000) {
			bindingResult.addError(new FieldError("item", "quantity", "수량은 1개 ~ 10,000개 사이입니다."));
		}
		if (item.getPrice() != null && item.getQuantity() != null) {
			int resultPrice = item.getPrice() * item.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.addError(new ObjectError("item", "가격 * 수량은 10,000원 이상이어야 합니다."));
			}
		}
		if (bindingResult.hasErrors()) {
			// model attribute에 별도로 담지 않아도 자동으로 들어감.
			// thymeleaf에서는 ${#fields.hasGlobalErrors()}로 글로벌 에러 여부, th.errors=*{name}로 필드 에러 여부를 확인할 수 있다.
			// th:errorclass는 th:filed로 감싸진 태그에 에러가 있을 때 클래스를 추가한다.
			return "binding-result-v1";
		}
		return "binding-result-v1";
	}

	/**
	 * // 사용자가 입력한 값을 유지하려면 아래처럼.
	 * BindingResult 파라미터 목록
	 * - objectName: @ModelAttribute의 objectName
	 * - filed: 검증 오류가 발생한 필드
	 * - rejectedValue: 사용자가 입력한 값(거절된 값)
	 * - bindingFailure: 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값
	 * - codes: 메시지 코드
	 * - arguments: 메시지 코드에서 사용할 인자
	 * - defaultMessage: 기본 오류 메시지
	 */
	@PostMapping("/valid-v2")
	public String bindingResultV2(@ModelAttribute Item item, BindingResult bindingResult) {
		// 검증 로직
		if(!StringUtils.hasText(item.getItemName())) {
			bindingResult.addError(new FieldError("item", "name", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
		}
		if(item.getPrice() < 1000 || item.getPrice() > 1000000) {
			bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000원 ~ 1,000,000원 사이입니다."));
		}
		if(item.getQuantity() < 1 || item.getQuantity() > 10000) {
			bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 1개 ~ 10,000개 사이입니다."));
		}
		if (item.getPrice() != null && item.getQuantity() != null) {
			int resultPrice = item.getPrice() * item.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량은 10,000원 이상이어야 합니다."));
			}
		}
		if (bindingResult.hasErrors()) {
			return "binding-result-v2";
		}
		return "binding-result-v2";
	}

	/**
	 * 오류 코드와 메시지 공통 처리 V1
	 * 메세지 소스를 쓰는 방법
	 */
	@PostMapping("/valid-v3")
	public String bindingResultV3(@ModelAttribute Item item, BindingResult bindingResult) {
		// 검증 로직
		if(!StringUtils.hasText(item.getItemName())) {
			bindingResult.addError(new FieldError("item", "name", item.getItemName(), false, new String[]{"required.item.itemName", "required.default"}, null, null));
		}
		if(item.getPrice() < 1000 || item.getPrice() > 1000000) {
			bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
		}
		if(item.getQuantity() < 1 || item.getQuantity() > 10000) {
			bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
		}
		if (item.getPrice() != null && item.getQuantity() != null) {
			int resultPrice = item.getPrice() * item.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
			}
		}
		if (bindingResult.hasErrors()) {
			return "binding-result-v3";
		}
		return "binding-result-v3";
	}

	/**
	 * 오류 코드와 메시지 공통 처리 V2
	 * rejectValue()를 사용하는 방법
	 */
	@PostMapping("/valid-v4")
	public String bindingResultV4(@ModelAttribute Item item, BindingResult bindingResult) {
		log.info("objectName]={}", bindingResult.getObjectName()); // objectName=item
		log.info("target]={}", bindingResult.getTarget()); // target=Item(id=null, name=null, price=null, quantity=null)

		// 검증 로직
		if (!StringUtils.hasText(item.getItemName())) {
			bindingResult.rejectValue("itemName", "required");
		}
		if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >
				1000000) {
			bindingResult.rejectValue("price", "range", new Object[]{1000,
					1000000}, null);
		}
		if (item.getQuantity() == null || item.getQuantity() > 10000) {
			bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
		}
		//특정 필드 예외가 아닌 전체 예외
		if (item.getPrice() != null && item.getQuantity() != null) {
			int resultPrice = item.getPrice() * item.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.reject("totalPriceMin", new Object[]{10000,
						resultPrice}, null);
			}
		}
		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return "binding-result-v4";
		}
		return "binding-result-v4";
	}
}
