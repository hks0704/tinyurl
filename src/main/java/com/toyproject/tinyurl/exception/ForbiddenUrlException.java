package com.toyproject.tinyurl.exception;

public class ForbiddenUrlException extends RuntimeException {
	public ForbiddenUrlException() {
		super("금지된 Url을 등록하려고 했습니다.");
	}

	public ForbiddenUrlException(String message, Throwable cause) {
		super(message, cause);
	}
}
