package com.toyproject.tinyurl.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.toyproject.tinyurl.dto.ErrorResponseDto;
import com.toyproject.tinyurl.exception.ForbiddenUrlException;
import com.toyproject.tinyurl.exception.UrlNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ForbiddenUrlException.class)
	public ResponseEntity<?> handleForbiddenUrlException(ForbiddenUrlException e) {
		log.error("GlobalExceptionHandler: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "금지된 Url을 등록하려고 했습니다."));
	}

	@ExceptionHandler(UrlNotFoundException.class)
	public ResponseEntity<?> handleUrlNotFoundException(UrlNotFoundException e) {
		log.error("GlobalExceptionHandler: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "해당하는 원본 Url이 발견되지 않았습니다."));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("GlobalExceptionHandler: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "IllegalArgumentException 오류"));
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException e) {
		log.error("GlobalExceptionHandler: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "NullPointerException 오류"));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		log.error("GlobalExceptionHandler: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "Exception 오류"));
	}

}
