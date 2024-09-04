package net.sprd.homework.response;

public class ValidationResponse {
	
	private final Integer status;
	
	private final String message;
	
	public ValidationResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
}
