package enum_env;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

import static enum_env.DeploymentEnvironment2.*;

public class OpenApiConfig2 {

	private static OpenAPI getOpenAPI() {
		if (CURRENT_DEPLOYMENT_ENVIRONMENT.getBaseUrl().isEmpty()) {
			return new OpenAPI().addServersItem(new Server().url("http://localhost:8820/"));
		}

		return new OpenAPI().addServersItem(new Server().url(CURRENT_DEPLOYMENT_ENVIRONMENT.getBaseUrl()));
	}
}
