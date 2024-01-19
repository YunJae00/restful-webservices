package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 10)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY) // 관꼐가 지연 로딩되는지 아니면 즉시 로딩되는지를 결정함 (동일한 퀴리에서 게시물과 사용자의 세부 정보를 검색하려고 한다면 게시물 세부정보와
										// 함께 가져오도록 요청하면 사용자 세부정보도 같이 가져오게됨) -> 다대일 관계의 기본값인데 여기서는 Lazy fetch를 함
	// user과는 반대로 다대 일 관계
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
}
