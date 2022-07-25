package com.example.demo.exception;

public class ServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String path;		

	public ServiceException(String message, String path) {
		super();
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
	
}
