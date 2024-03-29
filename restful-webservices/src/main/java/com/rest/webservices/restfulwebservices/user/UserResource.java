package com.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// http://localhost:8080/users

	// EntityModel 도메인 객체를 래핑하여 링크를 추가하는 모델
	// WebMvcLinkBuilder

	// HATEOAS 구현
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id:" + id);

		EntityModel<User> entityModel = EntityModel.of(user); // user클래스를 래핑하고 entitymodel을 생성하는거

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	// GET /users
//	@GetMapping("/users/{id}")
//	public User retrieveUser(@PathVariable int id) {
//
//		User user = service.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException("id:" + id);
//		}
//		return user;
//	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}

	// POST /users
	@PostMapping("/users")
	// @valid 유효성 검사 (프로퍼티 메소드인자 반환타입의 유효성을 확인하기 위함) -> 바인딩이 수행될 때 객체의 정의된 유효성 검증이
	// 자동으로 수행 -> user에 validation 추가
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		// service.save(user);
		// 응답 상태를 200에서 201로 바꿔줌
		// return ResponseEntity.created(null).build();

		// location header 에 현재 요천의 경로가 담겨 오고 거기에 user의 id가 붙여짐
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
