package crawling.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NaverWorldFinance {

	private String title;
	private String price;
	private String rate;
	private int priority;

	@Builder
	public NaverWorldFinance(String title, String price, String rate) {
		this.title = FinanceType.findAlias(title);
		this.price = price;
		this.rate = rate;
		this.priority = FinanceType.getPriority(title);
	}

	@Override
	public String toString() {
		return title.equals("비트코인") ?
				String.format("%s %s", title, price) : String.format("%s %s(%s)", title, price, rate);
	}

}
