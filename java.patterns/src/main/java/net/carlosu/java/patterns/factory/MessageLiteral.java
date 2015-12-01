package net.carlosu.java.patterns.factory;

import javax.enterprise.util.AnnotationLiteral;

public class MessageLiteral extends AnnotationLiteral<Message> implements Message{

	private static final long serialVersionUID = 1L;
	private Message.Type type;
	
	public MessageLiteral(Message.Type type){
		this.type = type;
	}

	public Message.Type value() {
		return type;
	}

}
