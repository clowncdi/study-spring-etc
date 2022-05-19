package enum_env;

import java.util.function.Supplier;

import org.springframework.http.HttpMethod;

import static enum_env.DeploymentEnvironment2.*;

public enum BBBCallbackRequest2 {
	UPDATE_USER_ID(
			HttpMethod.PATCH, () -> CURRENT_DEPLOYMENT_ENVIRONMENT.getBbbCallbackUrl() + "/api/v2/shop/user"),
	;

	private final HttpMethod httpMethod;
	private final Supplier<String> urlSupplier;

	BBBCallbackRequest2(HttpMethod httpMethod, Supplier<String> urlSupplier) {
		this.httpMethod = httpMethod;
		this.urlSupplier = urlSupplier;
	}
}
