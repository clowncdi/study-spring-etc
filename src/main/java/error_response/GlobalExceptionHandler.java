package error_response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import error_response.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 모든 예외를 잡을 수 있는 핸들러
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("handlerException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INTENAL_SERVER_ERROR);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INTENAL_SERVER_ERROR.getStatus()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
		final ErrorResponse response = ErrorResponse.of(errorCode, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
	}

	/**
	 * 계층화를 통한 Business Exception 처리를 위한 핸들러
	 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
		log.error("BusinessException", e);
		final ErrorCode errorCode = e.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
	}

	@ExceptionHandler({
			IllegalArgumentException.class,
			IllegalStateException.class
	})
	protected ResponseEntity<ErrorResponse> handleIIllegalArgumentException(Exception e) {
		log.error("handlerIllegalArgumentException", e);
		// 1. 에러 메시지와 코드를 지정한 값으로 전달하는 방식
		// final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
		// 2. 에러 객체를 그대로 전달하는 방식
		final ErrorResponse response = ErrorResponse.of(e);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getStatus()));
	}

	/**
	 * 지원하지 않은 HTTP Method 호출 시 발생하는 예외를 잡을 수 있는 핸들러
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("handleHttpRequestMethodNotSupportedException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.METHOD_NOT_ALLOWED.getStatus()));
	}

}
