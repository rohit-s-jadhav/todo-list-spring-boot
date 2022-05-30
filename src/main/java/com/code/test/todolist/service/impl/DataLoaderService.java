package com.code.test.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.code.test.todolist.model.User;
import com.code.test.todolist.model.UserRole;
import com.code.test.todolist.repository.UserRepo;

@Component
public class DataLoaderService implements ApplicationRunner {
	
	@Value("${todo.username}")
	private String userName;
	
	@Value("${todo.pwd}")
	private String pwd;

	@Autowired
    private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {
    	User user = new User();
    	user.setUsername(userName);
    	user.setPassword(passwordEncoder.encode(pwd));
    	user.setRole(UserRole.USER);
    	userRepo.saveAndFlush(user);
    }
}