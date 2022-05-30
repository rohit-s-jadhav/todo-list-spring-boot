package com.code.test.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.test.todolist.model.Todo;
import com.code.test.todolist.model.User;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
	
	List<Todo> findByUserAndActiveTrue(User user);

}
