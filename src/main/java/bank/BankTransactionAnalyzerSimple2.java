package bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple2 {

	/* CSV 소스파일 샘플
	30-01-2017,-100,Deliveroo
	30-01-2017,-50,Tesco
	01-02-2017,6000,Salary
	02-02-2017,2000,Royalties
	02-02-2017,-4000,Rent
	03-02-2017,3000,Tesco
	05-02-2017,-30,Cinema*/

	// 2. 1월 입출금 내역 합계 계산하기
	private static final String RESOURCES = "src/main/resources/";
	
	public static void main(final String... args) throws IOException {

		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);
		double total = 0d;
		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");	// 추가
		for (final String line : lines) {
			final String[] columns = line.split(",");
			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);				// 추가
			if (date.getMonth() == Month.JANUARY) {											//추가
				final double amount = Double.parseDouble(columns[1]);
				total += amount;
			}
		}

		System.out.println("The total for all transactions is " + total);
	}
}
