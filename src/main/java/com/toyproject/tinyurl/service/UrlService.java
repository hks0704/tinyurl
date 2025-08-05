package com.toyproject.tinyurl.service;

import com.toyproject.tinyurl.dto.UrlCreateRequestDto;
import com.toyproject.tinyurl.dto.UrlCreateResponseDto;

public interface UrlService {

    UrlCreateResponseDto createUrl(UrlCreateRequestDto requestDto);

    Boolean isExistUrl(String url);

    String findLongUrl(String shortUrl);

}
