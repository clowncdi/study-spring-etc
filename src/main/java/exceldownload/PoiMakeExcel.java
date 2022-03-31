package exceldownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiMakeExcel {

	public static String filePath = "D:\\temp";
	public static String fileNm = "test.xlsx";

	public static void main(String[] args) {

		// Workbook 생성
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Sheet 생성
		XSSFSheet sheet = workbook.createSheet("Car Data");

		// cell 스타일 설정
		// CellStyle greyCellStyle = workbook.createCellStyle();
		// applyCellStyle(greyCellStyle, new Color(231, 234, 236));
		//
		// CellStyle blueCellStyle = workbook.createCellStyle();
		// applyCellStyle(blueCellStyle, new Color(223, 235, 246));
		//
		// CellStyle bodyCellStyle = workbook.createCellStyle();
		// applyCellStyle(bodyCellStyle, new Color(255, 255, 255));

		// Sheet 헤더 저장
		int rownum = 0;
		final XSSFRow headerRow = sheet.createRow(rownum++);
		headerRow.createCell(0).setCellValue("회사");
		headerRow.createCell(1).setCellValue("이름");
		headerRow.createCell(2).setCellValue("가격");
		headerRow.createCell(3).setCellValue("세금");

		// 데이터 준비
		final CarExcelDto car1 = CarExcelDto.createCarDto()
				.company("현대")
				.name("소나타")
				.price(100)
				.build();
		final CarExcelDto car2 = CarExcelDto.createCarDto()
				.company("르노삼성")
				.name("QM6")
				.price(200)
				.build();
		final CarExcelDto car3 = CarExcelDto.createCarDto()
				.company("기아")
				.name("K7")
				.price(300)
				.build();
		final List<CarExcelDto> cars = Arrays.asList(car1, car2, car3);

		// sheet에 저장
		for (CarExcelDto car : cars) {
			final XSSFRow row = sheet.createRow(rownum++);
			int cellnum = 0;
			row.createCell(cellnum++).setCellValue(car.getCompany());
			row.createCell(cellnum++).setCellValue(car.getName());
			row.createCell(cellnum++).setCellValue(car.getPrice());
			row.createCell(cellnum).setCellFormula("C"+rownum+"*0.01");
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(filePath, fileNm));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// private static void applyCellStyle(CellStyle cellStyle, Color color) {
	// 	XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
	// 	xssfCellStyle.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
	// 	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	// 	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	// 	cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	// 	cellStyle.setBorderLeft(BorderStyle.THIN);
	// 	cellStyle.setBorderTop(BorderStyle.THIN);
	// 	cellStyle.setBorderRight(BorderStyle.THIN);
	// 	cellStyle.setBorderBottom(BorderStyle.THIN);
	// }
}
