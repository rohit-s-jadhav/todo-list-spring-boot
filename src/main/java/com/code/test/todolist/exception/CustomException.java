package com.code.test.todolist.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class CustomException {
	
	private String msg;
	
	private HttpStatus status;
	
	private Throwable throwable;
	
	private LocalDateTime timestamp;

	public CustomException(String msg, HttpStatus status, Throwable throwable, LocalDateTime timestamp) {
		super();
		this.msg = msg;
		this.status = status;
		this.throwable = throwable;
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
}
