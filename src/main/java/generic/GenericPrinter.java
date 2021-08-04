package generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericPrinter<T extends Material> {
	private T materail;

	public String toString() {
		return materail.toString();
	}
}
