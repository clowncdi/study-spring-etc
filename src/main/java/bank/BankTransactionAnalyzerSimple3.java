package bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankTransactionAnalyzerSimple3 {

	/* CSV 소스파일 샘플
	30-01-2017,-100,Deliveroo
	30-01-2017,-50,Tesco
	01-02-2017,6000,Salary
	02-02-2017,2000,Royalties
	02-02-2017,-4000,Rent
	03-02-2017,3000,Tesco
	05-02-2017,-30,Cinema*/

	// 3. SRP 단일 책임 원칙
	private static final String RESOURCES = "src/main/resources/";
	
	public static void main(final String... args) throws IOException {

		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final String fileName = args[0];														// 추가
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);	// 추가

		System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transactions is January " + selectInMonth(bankTransactions, Month.JANUARY));
	}

	// 입출금 내역 목록 처리 : 전체 금액 계산
	public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
		double total = 0d;
		for (final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}

	// 입출금 내역 목록 처리 : 특정 월별 기준
	public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
		final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}
		}
		return bankTransactionsInMonth;
	}

	// 파싱 로직을 추출해 한 클래스로 만듦
	public static class BankStatementCSVParser {
		private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		private BankTransaction parseFromCSV(final String line) {
			final String[] columns = line.split(",");
			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
			final double amount = Double.parseDouble(columns[1]);
			final String description = columns[2];

			return new BankTransaction(date, amount, description);
		}

		public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
			final List<BankTransaction> bankTransactions = new ArrayList<>();
			for (final String line : lines) {
				bankTransactions.add(parseFromCSV(line));
			}
			return bankTransactions;
		}
	}

	// 입출금 내역 도메인 클래스
	public static class BankTransaction {
		private final LocalDate date;
		private final double amount;
		private final String description;

		public BankTransaction(LocalDate date, double amount, String description) {
			this.date = date;
			this.amount = amount;
			this.description = description;
		}

		public LocalDate getDate() {
			return date;
		}

		public double getAmount() {
			return amount;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return "BankTransaction{" +
					"date=" + date +
					", amount=" + amount +
					", description='" + description + '\'' +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			BankTransaction that = (BankTransaction)o;
			return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date)
					&& Objects.equals(description, that.description);
		}

		@Override
		public int hashCode() {
			return Objects.hash(date, amount, description);
		}
	}
}
