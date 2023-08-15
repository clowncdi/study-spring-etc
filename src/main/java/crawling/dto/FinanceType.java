package crawling.dto;

public enum FinanceType {
	SP500(1, "S&P 500", "S&P500", "https://finance.naver.com/world/"),
	DOW(2,"다우 산업", "다우", "https://finance.naver.com/world/"),
	NASDAQ(3, "나스닥 종합", "나스닥", "https://finance.naver.com/world/"),
	WTI(4, "서부텍사스산 원유", "WTI", "https://m.stock.naver.com/marketindex/energy/CLcv1"),
	DXY(5, "달러인덱스", "달러인덱스", "https://m.stock.naver.com/marketindex/exchange/.DXY"),
	VIX(6, "VIX: 변동성 지수", "VIX", "https://m.stock.naver.com/worldstock/index/.VIX/total"),
	GOLD(7, "국제 금", "금", "https://m.stock.naver.com/marketindex/metals/GCcv1"),
	BTC(8, "비트코인", "비트코인", "https://m.stock.naver.com/crypto/UPBIT/BTC");

	private final int priority;
	private final String title;
	private final String alias;
	private final String url;

	public static String findAlias(String title) {
		for (FinanceType type : FinanceType.values()) {
			if (type.getTitle().equals(title)) {
				return type.getAlias();
			}
		}
		return title;
	}

	public static int getPriority(String title) {
		for (FinanceType type : FinanceType.values()) {
			if (type.getTitle().equals(title)) {
				return type.getPriority();
			}
		}
		return 0;
	}

	private FinanceType(int priority, String title, String alias, String url) {
		this.priority = priority;
		this.title = title;
		this.alias = alias;
		this.url = url;
	}

	public static boolean isExist(String title) {
		return title.equals("다우 산업") || title.equals("나스닥 종합") || title.equals("S&P 500");
	}

	public int getPriority() {
		return priority;
	}

	public String getTitle() {
		return title;
	}

	public String getAlias() {
		return alias;
	}

	public String getUrl() {
		return url;
	}

}
