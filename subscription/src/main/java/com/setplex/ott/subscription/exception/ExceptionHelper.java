package com.setplex.ott.subscription.exception;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(value = { NotfoundException.class })

	public ResponseEntity<Object> handleInvalidInputException(NotfoundException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("error", ex.getMessage());
        body.put("stack", ex.getCause());
        body.put("message", "Data not found");
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = { BusinessException.class })

	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { InternalServerErrorException.class })

	public ResponseEntity<Object> handleException(InternalServerErrorException ex) {

		// LOGGER.error("Exception: ",ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value = { BadRequestException.class })

	public ResponseEntity<Object> handleException(BadRequestException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = { InvalidDeviceTypeException.class })

	public ResponseEntity<Object> handleException(InvalidDeviceTypeException ex) {

		// LOGGER.error("Exception: ",ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}
}
