package validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bean Validation 사용
 * spring-boot-starter-validation 의존성 추가해야 됨
 * JPA 하이버네이트와 이름이 같지만 무관하다.
 * 스프링 부트는 LocalValidatorFactoryBean을 자동으로 글로벌 Validator로 등록한다.
 */
@RestController
public class Validation02 {

	/**
	 * @Validated 를 꼭 넣어줘야 함.
	 * 여러 검증기가 존재한다면 이 때 supports()가 사용된다.
	 * @Validated 는 스프링 전용 검증 애노테이션이고, 내부에 groups 라는 기능을 포함하고 있다.
	 * @Valid 는 javax 표준 검증 애노테이션으로 사용하려면 의존관계 추가가 필요하다.
	 * 바인딩에 성공한 필드만 Bean Validation 적용
	 * @ModelAttribute -> 각각의 필드 타입 변환시도 -> 변환에 성공한 필드만 Bean Validation 적용 / 실패하면 typeMismatch FieldError 추가
	 */
	@PostMapping("/add")
	public String addItemV1(@Validated @ModelAttribute ItemValid item) {
		return "item-v1";
	}

	/**
	 * @Validated groups 사용 방법
	 * 복잡도가 올라가서 실무에서 잘 사용하지 않는다.
	 * 실무에선 등록용 폼과 수정용 폼 객체를 분리해서 사용한다.
	 */
	@PostMapping("/add-v2")
	public String addItemV2(@Validated(SaveCheck.class) @ModelAttribute ItemValid item) {
		return "item-v2";
	}

	@PostMapping("/edit-v2")
	public String editItemV2(@Validated(UpdateCheck.class) @ModelAttribute ItemValid item) {
		return "item-v2";
	}
}
