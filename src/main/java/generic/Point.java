package generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point<T, V> {
	T x;
	V y;
}
