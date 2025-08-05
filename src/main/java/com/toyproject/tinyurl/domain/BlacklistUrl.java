package com.toyproject.tinyurl.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "blacklist_url")
public class BlacklistUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blacklistUrlNo;
	private String blockedUrl;
}
