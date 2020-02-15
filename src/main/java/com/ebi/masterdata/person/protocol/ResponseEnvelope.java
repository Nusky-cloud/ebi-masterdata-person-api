package com.ebi.masterdata.person.protocol;

import org.springframework.http.HttpStatus;

public class ResponseEnvelope<T> {
	
	private HttpStatus status;
	private String message;
	private T body;
	
	public ResponseEnvelope(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseEnvelope(HttpStatus status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
