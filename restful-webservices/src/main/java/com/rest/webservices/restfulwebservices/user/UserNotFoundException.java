package com.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	// 이렇게 하고 devtools 주석처리하면 오류가 페이지에 안뜸
	// 운영용으로 사용하면 jar로 하는데 추적 로그는 안뜸?
	public UserNotFoundException(String message) {
		super(message);
	}

}
