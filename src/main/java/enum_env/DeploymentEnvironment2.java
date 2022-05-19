package enum_env;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum DeploymentEnvironment2 {
	// after
	LOCAL("", ""),
	DEV("", "https://dev.aaa.co.kr/"),
	QA("https://shopqa.bbb.co.kr", "https://qa.aaa.co.kr/"),
	STAGE("https://shopstage.bbb.co.kr", "https://stage.aaa.co.kr/"),
	PROD("https://shop.bbb.com", "https://api.aaa.com/");

	public static final DeploymentEnvironment2 CURRENT_DEPLOYMENT_ENVIRONMENT;

	private final String bbbCallbackUrl;
	private final String baseUrl;

	DeploymentEnvironment2(String bbbCallbackUrl, String baseUrl) {
		this.bbbCallbackUrl = bbbCallbackUrl;
		this.baseUrl = baseUrl;
	}

	public String getBbbCallbackUrl() {
		return bbbCallbackUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	static {
		final String authServerName = Optional.ofNullable(System.getenv("AUTH_SERVER_NAME"))
				.orElse("")
				.toUpperCase(Locale.ROOT);
		CURRENT_DEPLOYMENT_ENVIRONMENT = Arrays.stream(DeploymentEnvironment2.values())
				.filter(environment -> environment.name().equals(authServerName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("알 수 없는 환경 변수"));
	}
}
