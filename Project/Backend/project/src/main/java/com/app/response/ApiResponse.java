package com.app.response;

public class ApiResponse {

	private String Message;
	private Boolean status;
	
	public ApiResponse() {}

	public ApiResponse(String message, Boolean status) {
		super();
		Message = message;
		this.status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
}
