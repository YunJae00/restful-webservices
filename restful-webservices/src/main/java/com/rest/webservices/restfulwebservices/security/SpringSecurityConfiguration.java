package com.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//기존 필터 체인에서 수정해야할 두가지
//restapi 에서는 http 기본 인증이라는걸 사용
//scrf를 해제해서 post요청을 보낼 수 있게 하는거
//기존 필터체인을 오버라이드하려면 체인 전체를 다시 정의해야함 -> configuration 파일 생성
@Configuration
public class SpringSecurityConfiguration {

	// 필터체인
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		1) All requests should be authenticated 모든 요청이 인증을 받아야함
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

//		2) If a request is not authenticated, a web page is shown
		// ctrl+shift+T 클래스 검색 customizer -> withDefault 찾고 copy qualified name ->
		// import static 으로 하면 됨
		// -> 이러면 접속시 팝업창 뜨며 사용자 이름 비밀번호 물음
		http.httpBasic(withDefaults());

//		3) CSRF -> POST, PUT
		// csrf 해제하는거
		http.csrf().disable();

		return http.build();
	}

}