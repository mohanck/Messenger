package org.mohan.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6914430508198333958L;
	
	private String message;

	public DataNotFoundException(String message) {
		super(message);
		this.message = message; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
