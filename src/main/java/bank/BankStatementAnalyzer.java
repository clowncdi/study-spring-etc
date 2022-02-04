package bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import static bank.BankTransactionAnalyzerSimple3.*;

public class BankStatementAnalyzer {
	/* CSV 소스파일 샘플
	30-01-2017,-100,Deliveroo
	30-01-2017,-50,Tesco
	01-02-2017,6000,Salary
	02-02-2017,2000,Royalties
	02-02-2017,-4000,Rent
	03-02-2017,3000,Tesco
	05-02-2017,-30,Cinema*/

	// 4. BankStatementProcessor 클래스를 이용해 입출금 내역 목록 처리
	private static final String RESOURCES = "src/main/resources/";
	private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

	public static void main(final String... args) throws IOException {

		final String fileName = args[0];
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		collecSummary(bankStatementProcessor);
	}

	private static void collecSummary(BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
		System.out.println("The total for transactions in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("The total for transactions in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
		System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));
	}
}
