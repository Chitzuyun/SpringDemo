package com.example.demo.exception;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private HttpStatus code;
	
	private String path;
	
	private Date timestamp;
	
	private String message;	
	
	public ErrorResponse() {
	}

	public ErrorResponse(HttpStatus code, String path, Date timestamp, String message) {
		super();
		this.code = code;
		this.path = path;
		this.timestamp = timestamp;
		this.message = message;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", path=" + path + ", timestamp=" + timestamp + ", message=" + message
				+ "]";
	}
	
	
}
