package com.tabularize.app.wrapper;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ResponseWrapper<T> {
	
	private boolean status;
   
	private int statusCode;
   
	private String statusMessage;
	
    private String message;
    
    private T data;

	public ResponseWrapper(HttpStatus httpStatus,  String message, T data) {
		super();
		this.statusCode = httpStatus.value();
		
		this.statusMessage = httpStatus.toString();
		
		this.status = ! httpStatus.isError();
		
		this.message = message;
		
		this.data = data;
	}

	public ResponseWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
