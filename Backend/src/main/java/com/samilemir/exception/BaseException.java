package com.samilemir.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepErrorMessage());
	}

}
