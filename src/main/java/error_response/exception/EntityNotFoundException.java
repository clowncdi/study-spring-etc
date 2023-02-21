package error_response.exception;

import error_response.ErrorCode;

public class EntityNotFoundException extends BusinessException{
	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
