package com.code.test.todolist.service;

import java.util.List;

import com.code.test.todolist.to.TodoTO;

public interface TodoService {

	public List<TodoTO> getAllTodos(String authToken);
	
	public TodoTO addTodo(TodoTO todoTO, String authToken);
	
	public Boolean updateTodo(Long todoId);

	public Boolean removeTodo(Long todoId);
	
}
