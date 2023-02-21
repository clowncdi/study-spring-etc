package error_response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
	INTENAL_SERVER_ERROR(500, "C002", "Server Error"),
	METHOD_NOT_ALLOWED(405, "C003", "지원하지 않은 HTTP Method 입니다."),
	;

	private final int status;	// HTTP Status Code
	private final String code;
	private final String message;
}
