package com.code.test.todolist.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.test.todolist.service.TodoService;
import com.code.test.todolist.to.TodoTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // UI port
public class TodoRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoRest.class);
	
	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public @ResponseBody List<TodoTO> getTodos(@RequestHeader("Authorization") String authToken) {
		LOGGER.info("get todos");
		List<TodoTO> todos = todoService.getAllTodos(authToken);
		return todos;
	}
	
	@PostMapping("/todo")
	public @ResponseBody TodoTO addTodo(@RequestBody TodoTO todo, @RequestHeader("Authorization") String authToken) {
		LOGGER.info("add todo");
		todo = todoService.addTodo(todo, authToken);
		return todo;
	}
	
	@PutMapping("/todo")
	public @ResponseBody Boolean changeStatusTodo(@RequestBody Long todoId) {
		LOGGER.info("change todo");
		Boolean isUpdated = todoService.updateTodo(todoId);
		return isUpdated;
	}
	
	@DeleteMapping("/todo/{todoId}")
	public @ResponseBody Boolean deleteTodo(@PathVariable Long todoId) {
		LOGGER.info("delete todo");
		Boolean isRemoved = todoService.removeTodo(todoId);
		return isRemoved;
	}

}
