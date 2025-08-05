package com.toyproject.tinyurl.exception;

public class UrlNotFoundException extends RuntimeException {

	public UrlNotFoundException(String shortUrl) {
		super(shortUrl + "에 해당하는 원본 Url이 발견되지 않았습니다.");
	}

	public UrlNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
