package com.code.test.todolist.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.code.test.todolist.to.TodoTO;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false)
	private Long todoId;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private Boolean todoStatus;
	
	@Column
	private Boolean active = true;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime lastModified;

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public TodoTO convertoTO() {
		TodoTO todoTO = new TodoTO();
		todoTO.setTodoId(todoId);
		todoTO.setUserId(user.getUserId());
		todoTO.setTitle(title);
		todoTO.setTodoStatus(todoStatus);
		todoTO.setLastModified(lastModified);
		todoTO.setDescription(description);
		return todoTO;
	}
}