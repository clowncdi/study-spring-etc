package generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreeDPrinter1 {
	private Powder material;

	public String toString() {
		return "재료는 Powder 입니다.";
	}
}
