package com.code.test.todolist.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTests {

	@Autowired
	private LoginRest loginRest;
	
	@Autowired
	private TodoRest todoRest;

	@Test
	public void contextLoadLoginRest() throws Exception {
		assertThat(loginRest).isNotNull();
	}
	
	@Test
	public void contextLoadTodoRest() throws Exception {
		assertThat(todoRest).isNotNull();
	}
}
