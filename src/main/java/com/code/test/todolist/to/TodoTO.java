package com.code.test.todolist.to;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TodoTO {

	private Long todoId;
	
	private Long userId;

	private String title;

	private String description;

	private Boolean todoStatus;

	private LocalDateTime dateCreated;

	private LocalDateTime lastModified;

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getTodoStatus() {
		return todoStatus;
	}

	public void setTodoStatus(Boolean todoStatus) {
		this.todoStatus = todoStatus;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public TodoTO(Long userId, String title, String description, Boolean todoStatus, LocalDateTime dateCreated,
			LocalDateTime lastModified) {
		super();
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.todoStatus = todoStatus;
		this.dateCreated = dateCreated;
		this.lastModified = lastModified;
	}

	public TodoTO() {
		super();
	}
	
}
