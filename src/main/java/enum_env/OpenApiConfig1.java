package enum_env;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

public class OpenApiConfig1 {

	private OpenAPI getOpenAPI() {
		if (DeploymentEnvironment1.isDev()) {
			return new OpenAPI()
					.addServersItem(new Server().url("https://dev.aaa.co.kr/"))
					.addServersItem(new Server().url("http://localhost:8820/"));
		}

		if (DeploymentEnvironment1.isQA()) {
			return new OpenAPI()
					.addServersItem(new Server().url("https://qa.aaa.co.kr/"))
					.addServersItem(new Server().url("http://localhost:8820/"));
		}

		if (DeploymentEnvironment1.isStage()) {
			return new OpenAPI()
					.addServersItem(new Server().url("https://stage.aaa.co.kr/"))
					.addServersItem(new Server().url("http://localhost:8820/"));
		}

		if (DeploymentEnvironment1.isProd()) {
			return new OpenAPI()
					.addServersItem(new Server().url("https://api.aaa.com/"))
					.addServersItem(new Server().url("http://localhost:8820/"));
		}

		return new OpenAPI()
				.addServersItem(new Server().url("http://localhost:8820/"));
	}

}
