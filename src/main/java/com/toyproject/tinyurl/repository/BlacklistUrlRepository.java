package com.toyproject.tinyurl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.tinyurl.domain.BlacklistUrl;

public interface BlacklistUrlRepository extends JpaRepository<BlacklistUrl, Long> {
	Optional<BlacklistUrl> findByBlockedUrl(String blockedUrl);
}
