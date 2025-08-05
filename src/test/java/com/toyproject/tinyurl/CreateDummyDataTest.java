package com.toyproject.tinyurl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.toyproject.tinyurl.domain.BlacklistUrl;
import com.toyproject.tinyurl.domain.Url;
import com.toyproject.tinyurl.repository.BlacklistUrlRepository;
import com.toyproject.tinyurl.repository.UrlRepository;
import com.toyproject.tinyurl.util.Base62Util;

@SpringBootTest
public class CreateDummyDataTest {
	@Autowired
	private UrlRepository urlRepository;
	@Autowired
	private BlacklistUrlRepository blacklistUrlRepository;

	@Autowired
	private Base62Util base62Util;

	@Test
	void createDummyData() {
		for (long i = 1L; i <= 20000; i++) {
			String longUrl = String.format("https://myTestSampleUrl%d.com/", i);
			Url url = Url.builder().longUrl(longUrl).build();
			url = urlRepository.save(url);
			Long urlNo = url.getUrlNo();
			String shortUrl = base62Util.encoding(urlNo);
			url.updateShortUrl(shortUrl);
			urlRepository.save(url);
		}
	}

	@Test
	void createBlacklistDummyData() {
		BlacklistUrl url1 = BlacklistUrl.builder().blockedUrl("https://forbiddenSite.com/").build();
		BlacklistUrl url2 = BlacklistUrl.builder().blockedUrl("https://virusSite.com/").build();
		BlacklistUrl url3 = BlacklistUrl.builder().blockedUrl("https://blackblackSite.com/").build();
		List<BlacklistUrl> blacklistUrls = new ArrayList<>();
		blacklistUrls.add(url1);
		blacklistUrls.add(url2);
		blacklistUrls.add(url3);
		blacklistUrlRepository.saveAll(blacklistUrls);
	}
}
