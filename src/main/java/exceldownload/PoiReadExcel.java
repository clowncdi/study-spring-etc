package exceldownload;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static exceldownload.PoiMakeExcel.*;

public class PoiReadExcel {
	public static void main(String[] args) {
		try {
			final FileInputStream file = new FileInputStream(new File(filePath, fileNm));

			// 엑셀 파일로 Workbook instance 생성
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// 수식하는 셀이 있을 경우 사용
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			// 첫번째 sheet 가져오기
			final XSSFSheet sheet = workbook.getSheetAt(0);

			// 모든 row 조회
			final Iterator<Row> rows = sheet.iterator();
			while (rows.hasNext()) {
				final Row row = rows.next();

				// 각각의 행 모든 cell 순회
				final Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					final Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
						case NUMERIC:
							System.out.print(cell.getNumericCellValue()+"\t");
							break;
						case FORMULA:
							System.out.println(evaluator.evaluateInCell(cell) +"\t");
							break;
						default:
							System.out.print(cell.getStringCellValue()+"\t");
							break;
					}
				}
				System.out.println();
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
