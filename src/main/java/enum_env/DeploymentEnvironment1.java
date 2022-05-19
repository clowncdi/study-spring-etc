package enum_env;

import java.util.Locale;
import java.util.Optional;

public enum DeploymentEnvironment1 {
	// before
	DEV, QA, STAGE, PROD;

	private static final String AUTH_SERVER_NAME = Optional.ofNullable(System.getenv("AUTH_SERVER_NAME"))
			.orElse("");

	public static boolean isDev() {
		return AUTH_SERVER_NAME.equals(DEV.name().toLowerCase(Locale.ROOT));
	}

	public static boolean isQA() {
		return AUTH_SERVER_NAME.equals(QA.name().toLowerCase(Locale.ROOT));
	}

	public static boolean isStage() {
		return AUTH_SERVER_NAME.equals(STAGE.name().toLowerCase(Locale.ROOT));
	}

	public static boolean isProd() {
		return AUTH_SERVER_NAME.equals(PROD.name().toLowerCase(Locale.ROOT));
	}
}
