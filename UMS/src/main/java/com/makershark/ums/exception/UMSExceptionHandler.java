package com.makershark.ums.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class UMSExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
	//	log.error("user not found exception details -> {}", ex);
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("error", ex.getMessage());
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericException(Exception e) {
		log.error("general exception details -> {}", e);
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("error", "Internal server error,Please try again.");
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserCreationFailureException.class)
	public ResponseEntity<Map<String, String>> handleUserCreationFailureException(UserCreationFailureException ue) {
		log.error("user creation exception details -> {}", ue);
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("error", ue.getMessage());
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException me) {
		log.error("user creation exception details -> {}", me);
		Map<String, List<String>> errorMap = new HashMap<>();
		List<String> errors = new ArrayList<>();
		me.getAllErrors().forEach(error -> {
			errors.add(error.getDefaultMessage());
		});
		errorMap.put("errors", errors);
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
}
