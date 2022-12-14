package com.cos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.model.User;

//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 Ioc됩니다. JpaRepository를 상속했기 때문에...
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
