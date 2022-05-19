package enum_env;

import java.util.function.Supplier;

import org.springframework.http.HttpMethod;

public enum BBBCallbackRequest1 {
	UPDATE_USER_ID(HttpMethod.PATCH, () -> getBaseUrl() + "/api/shop/user"),
	;

	private final HttpMethod httpMethod;
	private final Supplier<String> urlSupplier;

	BBBCallbackRequest1(HttpMethod httpMethod, Supplier<String> urlSupplier) {
		this.httpMethod = httpMethod;
		this.urlSupplier = urlSupplier;
	}

	private static String getBaseUrl() {
		if (DeploymentEnvironment1.isProd()) {
			return "https://shop.bbb.com";
		}

		if (DeploymentEnvironment1.isStage()) {
			return "https://shopstage.bbb.co.kr";
		}

		if (DeploymentEnvironment1.isQA()) {
			return "https://shopqa.bbb.co.kr";
		}

		throw new IllegalStateException("local, dev 에서는 콜백하지 않습니다.");
	}

}
