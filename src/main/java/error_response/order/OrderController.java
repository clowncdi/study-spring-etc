package error_response.order;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	/**
	 * 주문
	 * 1. 주문에 대한 결제는 무통장 입금, 카드 결제가 가능하다.
	 * 2. 결제 수단은 2개 중 반드시 1개를 선택하여 진행한다.
	 * 3. 무통장 결제는 환불 받을 AccountNumber, bankCode, holder 필수이다.
	 * 4. 신용 카드 결제는 number, brand, csv 정보가 필수이다.
	 * ---------------------------------------------------------------
	 * 신용 카드 결제 -> 사용 카드 정보가 필수
	 * 무통장 결제 -> 계좌 정보가 필수
	 */
	@PostMapping
	public OrderSheetRequest order(@RequestBody @Valid final OrderSheetRequest request) {
		return request;
	}
}
