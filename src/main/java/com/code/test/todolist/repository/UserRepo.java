package com.code.test.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.test.todolist.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
