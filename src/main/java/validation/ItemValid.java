package validation;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

// Object Error 처리. 기능이 약해서 사용 권장 안함
// @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원을 넘게 해주세요.")
@Data
public class ItemValid {

	@NotNull(groups = UpdateCheck.class)
	private Long id;

	@NotBlank(message = "공백 안됨", groups = {SaveCheck.class, UpdateCheck.class})
	private String itemName;

	@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
	@Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
	private Integer price;

	@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
	@Max(value = 9999, groups = {SaveCheck.class})
	private Integer quantity;

	public ItemValid(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
