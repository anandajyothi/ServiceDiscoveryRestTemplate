package com.setplex.ott.subscription.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDeviceTypeException extends RuntimeException {
	public InvalidDeviceTypeException() {
		super();
	}

	public InvalidDeviceTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDeviceTypeException(String message) {
		super(message);
	}

	public InvalidDeviceTypeException(Throwable cause) {
		super(cause);
	}
}
