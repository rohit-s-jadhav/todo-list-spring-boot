package com.code.test.todolist.exception;

public class CustomApiException extends RuntimeException {

	private static final long serialVersionUID = 295263195765333852L;
	
	public CustomApiException(String msg) {
		super(msg);
	}

	public CustomApiException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
