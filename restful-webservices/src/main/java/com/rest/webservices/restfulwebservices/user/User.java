package com.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details") // h2에서 user가 keyword여서 이름 바꿔줘야함
public class User {

	// jpa연결 하려면 생성자가 필요하다 함
	protected User() {

	}

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should have atleast 2 characters") // VALID 설정
	// @JsonProperty("user_name") // 응답의 필드 이름을 커스터마이징 함
	private String name;

	@Past(message = "Birth Date should be in the past")
	// @JsonProperty("birth_date")
	private LocalDate birthDate;

	@OneToMany(mappedBy = "user") // 속성으로 매핑하기 특정 관꼐를 가지는 필드가 뭔지 넣어줌
	@JsonIgnore
	private List<Post> posts; // post와의 관계 일대 다 관계

	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
