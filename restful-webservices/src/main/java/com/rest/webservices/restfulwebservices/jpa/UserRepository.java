package com.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {// 어떤 entity 관리, id필드의 타입
//userResource 에서 UserRepository를 이용해 db와 연결
}
