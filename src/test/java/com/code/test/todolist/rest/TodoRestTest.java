package com.code.test.todolist.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.code.test.todolist.service.TodoService;
import com.code.test.todolist.to.TodoTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TodoRestTest {
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private TodoService todoService;
	
	@BeforeAll
	public void setupMockSecurity() {
		this.mockMvc = MockMvcBuilders
		          .webAppContextSetup(context)
		          .apply(springSecurity())
		          .build();
	}
	
	@WithUserDetails("test")
	@Test
	public void testGetTodos() throws Exception{
		
		List<TodoTO> todos = new ArrayList<>();
		
		when(todoService.getAllTodos("token")).thenReturn(todos);
		
		 MvcResult result = mockMvc.perform(
				get("/todos")
				.header("Authorization", "token")
				.contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk()).andReturn();
		 
		 assertEquals(200, result.getResponse().getStatus());
	}
	
	@WithUserDetails("test")
	@Test
	public void testAddTodo() throws Exception{
		
		TodoTO todo = new TodoTO(1L, "test-title", "test-desc", false, LocalDateTime.now(), LocalDateTime.now());
		
		when(todoService.addTodo(todo, "token")).thenReturn(todo);
		
		 MvcResult result = mockMvc.perform(
				post("/todo")
				.header("Authorization", "token")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(todo)))
				.andExpect(status().isOk()).andReturn();
		 
		 assertEquals(200, result.getResponse().getStatus());
	}
	
	@WithUserDetails("test")
	@Test
	public void testChangeStatusTodo() throws Exception{
		
		Long todoId = 1l;
		when(todoService.updateTodo(todoId)).thenReturn(true);
		
		 MvcResult result = mockMvc.perform(
				put("/todo")
				.header("Authorization", "token")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(todoId)))
				.andExpect(status().isOk()).andReturn();
		 
		 assertEquals(200, result.getResponse().getStatus());
	}
	
	@WithUserDetails("test")
	@Test
	public void testDeleteTodo() throws Exception{
		
		Long todoId = 1l;
		when(todoService.removeTodo(todoId)).thenReturn(true);
		
		 MvcResult result = mockMvc.perform(
				delete("/todo/{todoId}", todoId)
				.header("Authorization", "token"))
				.andExpect(status().isOk()).andReturn();
		 
		 assertEquals(200, result.getResponse().getStatus());
	}

}
