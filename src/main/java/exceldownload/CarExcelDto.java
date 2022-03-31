package exceldownload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder(builderMethodName = "createCarDto")
public class CarExcelDto {
	private final String company;
	private final String name;
	private final int price;


}
