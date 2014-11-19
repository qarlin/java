package com.hatumruna.taskmanager.exception;

public class BusinessException extends Exception {
	private String localMessage;
	
	public BusinessException(String message){
		this.localMessage = message;
		
	}

	public String getLocalMessage() {
		return localMessage;
	}

	public void setLocalMessage(String message) {
		this.localMessage = message;
	}
}
