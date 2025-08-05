package com.toyproject.tinyurl.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.tinyurl.domain.Url;
import com.toyproject.tinyurl.dto.UrlCreateRequestDto;
import com.toyproject.tinyurl.dto.UrlCreateResponseDto;
import com.toyproject.tinyurl.exception.ForbiddenUrlException;
import com.toyproject.tinyurl.exception.UrlNotFoundException;
import com.toyproject.tinyurl.repository.BlacklistUrlRepository;
import com.toyproject.tinyurl.repository.UrlRepository;
import com.toyproject.tinyurl.util.Base62Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {
	private final UrlRepository urlRepository;
	private final BlacklistUrlRepository blacklistUrlRepository;
	private final Base62Util base62Util;

	@Transactional
	@Override
	public UrlCreateResponseDto createUrl(UrlCreateRequestDto requestDto) {
		log.debug("debug Test");
		// 지금은 DB PK id값으로 UUID 처리하되
		// 추후 스노플레이크 방식으로 확장성을 고려할 것
		String longUrl = requestDto.getUrl();
		Optional<Url> urlOptional = urlRepository.findByLongUrl(longUrl);
		if (blacklistUrlRepository.findByBlockedUrl(longUrl).isPresent()) {
			log.debug("위험한 사이트 입력하면 안돼요.");
			throw new ForbiddenUrlException();
		}
		if (urlOptional.isEmpty()) {
			Url url = Url.builder().longUrl(longUrl).build();
			url = urlRepository.save(url);
			Long urlNo = url.getUrlNo();
			log.debug("인코딩 이전 정수값: {}", urlNo);
			String shortUrl = base62Util.encoding(urlNo);
			log.debug("인코딩 이후 shortUrl값: {}", shortUrl);
			url.updateShortUrl(shortUrl);
			return new UrlCreateResponseDto(shortUrl);
		}
		log.debug("이미 DB에 저장된 URL");
		return new UrlCreateResponseDto(urlOptional.get().getShortUrl());
	}

	@Override
	public Boolean isExistUrl(String url) {
		return urlRepository.findByLongUrl(url).isEmpty();
	}

	@Override
	public String findLongUrl(String shortUrl) {
		return urlRepository.findByShortUrl(shortUrl)
			.orElseThrow(() -> new UrlNotFoundException(shortUrl)).getLongUrl();
	}
}
