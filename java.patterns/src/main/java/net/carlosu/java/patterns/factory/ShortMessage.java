package net.carlosu.java.patterns.factory;

public class ShortMessage implements MessageType {
	
	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
