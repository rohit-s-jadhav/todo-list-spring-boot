package com.code.test.todolist.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.code.test.todolist.to.UserTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class LoginRestTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	@BeforeAll
	public void setupMockSecurity() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void testLogin() throws Exception {

		UserTO user = new UserTO("test", "pwd123");

		MvcResult result = mockMvc.perform(
				post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		assertEquals(200, result.getResponse().getStatus());

	}
	
	@WithUserDetails("test")
	@Test
	public void testLogout() throws Exception {

		MvcResult result = mockMvc.perform(
				post("/log-out")
				.header("Authorization", "token"))
				.andExpect(status().isOk()).andReturn();
		 
		 assertEquals(200, result.getResponse().getStatus());

	}

}
