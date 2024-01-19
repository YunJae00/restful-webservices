package com.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {// 어떤 entity 관리, id필드의 타입
//userResource 에서 UserRepository를 이용해 db와 연결
}
