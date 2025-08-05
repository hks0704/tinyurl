package com.toyproject.tinyurl.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
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
@Table(name = "url")
public class Url {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long urlNo;

	// 단축 URL 대소문자 구별을 위해 필요한 설정
	@Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
	private String shortUrl;

	private String longUrl;

	public void updateShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
