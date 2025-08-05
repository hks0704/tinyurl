package com.toyproject.tinyurl.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.tinyurl.dto.UrlCreateRequestDto;
import com.toyproject.tinyurl.dto.UrlCreateResponseDto;
import com.toyproject.tinyurl.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class UrlController {

	private final UrlService urlService;

	@PostMapping("/data/shorten")
	public ResponseEntity<?> createUrl(@RequestBody UrlCreateRequestDto requestDto) {
		UrlCreateResponseDto responseDto = urlService.createUrl(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws
		URISyntaxException {
		log.debug("입력된 shortUrl 값: {}", shortUrl);
		String longUrl = urlService.findLongUrl(shortUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new URI(longUrl));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

}
