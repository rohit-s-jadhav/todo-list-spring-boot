package com.code.test.todolist.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.code.test.todolist.model.Todo;
import com.code.test.todolist.model.User;
import com.code.test.todolist.repository.TodoRepo;
import com.code.test.todolist.service.TodoService;
import com.code.test.todolist.session.SessionManager;
import com.code.test.todolist.to.TodoTO;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private TodoRepo todoRepo;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public List<TodoTO> getAllTodos(String authToken) {

		List<TodoTO> todos = new ArrayList<TodoTO>();
		User currentUser = findUserBySession(authToken);
		if (!ObjectUtils.isEmpty(currentUser)) {
			List<Todo> todoList = todoRepo.findByUserAndActiveTrue(currentUser);
			if (!CollectionUtils.isEmpty(todoList)) {
				todos = todoList.stream().map(Todo::convertoTO).collect(Collectors.toList());
			}
		}
		return todos;
	}

	@Override
	public TodoTO addTodo(TodoTO todoTO, String authToken) {
		Todo todo = new Todo();
		User currentUser = findUserBySession(authToken);
		if (!ObjectUtils.isEmpty(currentUser)) {
			todo.setTitle(todoTO.getTitle());
			todo.setTodoStatus(todoTO.getTodoStatus());
			todo.setDescription(todoTO.getDescription());
			todo.setUser(currentUser);
			todo = todoRepo.saveAndFlush(todo);
		}
		return todo.convertoTO();
	}

	@Override
	public Boolean updateTodo(Long todoId) {
		Boolean isUpdated = false;
		if (!ObjectUtils.isEmpty(todoId)) {
			Optional<Todo> todoOp = todoRepo.findById(todoId);
			if (todoOp.isPresent()) {
				Todo todo = todoOp.get();
				todo.setTodoStatus(!todo.getTodoStatus());
				todo = todoRepo.saveAndFlush(todo);
				isUpdated = true;
			}
		}
		return isUpdated;
	}

	@Override
	public Boolean removeTodo(Long todoId) {
		Boolean isRemoved = false;
		if (!ObjectUtils.isEmpty(todoId)) {
			Optional<Todo> todoOp = todoRepo.findById(todoId);
			if (todoOp.isPresent()) {
				Todo todo = todoOp.get();
				todo.setActive(false);
				todo = todoRepo.saveAndFlush(todo);
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	private User findUserBySession(String authToken) {
		String username = sessionManager.getUsernameBySession(authToken);
		return (User) customUserDetailsService.loadUserByUsername(username);
	}

}
