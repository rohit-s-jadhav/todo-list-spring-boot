package com.code.test.todolist.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.code.test.todolist.exception.CustomApiException;
import com.code.test.todolist.model.User;
import com.code.test.todolist.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepo.findByUsername(username);
		user.orElseThrow(() -> new CustomApiException("User not found with name : "+ username));
		return user.get();
	}

}
