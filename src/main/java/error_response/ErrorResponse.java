package error_response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private String message;
	private int status;
	private List<FieldError> errors;
	private String code;
	private LocalDateTime timestamp;

	private ErrorResponse(String message, int status, String code) {
		this.message = message;
		this.status = status;
		this.errors = new ArrayList<>();
		this.code = code;
		this.timestamp = LocalDateTime.now();
	}

	private ErrorResponse(ErrorCode errorcode, List<FieldError> errors) {
		this.message = errorcode.getMessage();
		this.status = errorcode.getStatus();
		this.errors = errors;
		this.code = errorcode.getCode();
		this.timestamp = LocalDateTime.now();
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode.getMessage(), errorCode.getStatus(), errorCode.getCode());
	}

	public static ErrorResponse of(Exception e) {
		return new ErrorResponse(e.getMessage(), 500, "C002");
	}

	public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
		return new ErrorResponse(errorCode, FieldError.of(bindingResult));
	}

	@Getter
	public static class FieldError {
		private String field;
		private String value;
		private String reason;

		public FieldError(String field, String value, String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}

		public static List<FieldError> of(BindingResult bindingResult) {
			return bindingResult.getFieldErrors()
					.stream()
					.map(error -> new FieldError(
							error.getField(),
							error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
							error.getDefaultMessage()
							)
					).collect(Collectors.toList());
		}
	}
}
