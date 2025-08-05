# 📌 URL 압축기 (TinyURL)

짧은 URL을 생성하고 관리할 수 있는 단축 URL 서비스

---

## 🧾 목차 (Table of Contents)

1. [프로젝트 소개](#프로젝트-소개)
2. [기술 스택](#기술-스택)
3. [주요 기능](#주요-기능)
4. [문제 해결 경험](#문제-해결-경험)
5. [실행 방법](#실행-방법)
6. [향후 개선 계획](#향후-개선-계획)

---

## 📌 프로젝트 소개

이 프로젝트는 **학습목적** 을 위해 개발되었습니다.  
주요 목표는 다음과 같습니다:

- HTTP 리다이렉트 개념 학습
- Base62 인코딩 개념 학습
- JPA를 활용한 SpringBoot CRUD 복습
- @ControllerAdvice를 활용한 예외처리 방식 학습

💡 **이 프로젝트를 통해 학습하거나 실습한 기술**

- Spring Boot 기반 API 서버 개발
- JPA를 활용한 CRUD 로직 구현

---

## ⚙ 기술 스택

| 구분       | 기술                                 |
|------------|------------------------------------|
| Backend    | Java, Spring Boot, JPA (Hibernate) |
| DB         | MySQL                              |
| Build Tool | Gradle                             |
| Tools      | IntelliJ, Git, GitHub, Postman     |

---

## 🚀 주요 기능

- ✅ 단축 URL 생성
- ✅ 원본 URL로 리다이렉트
- ✅ 블랙리스트 URL 등록 방지

---

## 🧩 문제 해결 경험

### 1. 문제: 단축 URL을 담아 GET 요청시 쿼리 unique 오류 발생

- **상황**: JPA repository에서 column 조회시 대소문자 구별 없이 여러 개의 record를 조회함
- **해결**: 엔티티 설정시 `@Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")` 을 추가해 column
  대소문자를 구분함

### 2. 문제: 악의적인 사용자가 입력한 URL이 단축 URL로 등록될 수 있음

- **해결**: `BlacklistUrl` 엔티티를 만들어서 service 레이어에서 블랙리스트에 등록된 URL을 압축하려 시도할 때 예외 처리

---

## 🛠 실행 방법

### 1. 환경 설정

* `application.properties` 또는 `application.yml` 파일을 다음과 같은 형태로 생성:
* 아래 예시

```yml
spring:
  application:
    name: tinyurl

  # MySQL DB
  datasource:
    url: jdbc:mysql://{YOUR_DOMAIN}:{PORT}/{DB_NAME}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: { NAME }
    password: { PASSWORD }

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true
        use_sql_comments: true
    show-sql: true

logging:
  level:
    root: DEBUG
```

---

## 📈 향후 개선 계획

* [ ] 테스트코드 작성 완성하기
* [ ] URL 삭제 기능 추가
* [ ] Redis 캐싱 적용으로 성능 개선
* [ ] 사용자 통계 대시보드 추가
* [ ] CI/CD 파이프라인 구성
* [ ] UUID를 테이블의 PK 값이 아닌 스노플레이크 생성값으로 변경

---

## 🔍 참고자료

* 가상 면접 사례로 배우는 대규모 시스템 설계 기초
