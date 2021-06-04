package com.setplex.ott.subscription.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotfoundException extends RuntimeException {
	public NotfoundException() {
        super();
    }
    public NotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotfoundException(String message) {
        super(message);
    }
    public NotfoundException(Throwable cause) {
        super(cause);
    }
}
