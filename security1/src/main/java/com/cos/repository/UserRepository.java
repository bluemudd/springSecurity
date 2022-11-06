package com.cos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
