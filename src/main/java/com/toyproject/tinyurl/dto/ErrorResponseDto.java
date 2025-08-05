package com.toyproject.tinyurl.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
	private final HttpStatus status;
	private final String message;

	public ErrorResponseDto(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
