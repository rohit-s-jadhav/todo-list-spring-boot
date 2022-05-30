package com.code.test.todolist.to;

import com.code.test.todolist.model.UserRole;

public class UserTO {

	private String username;
	
	private String password;
	
	private UserRole role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserTO() {
		super();
	}
	
}
