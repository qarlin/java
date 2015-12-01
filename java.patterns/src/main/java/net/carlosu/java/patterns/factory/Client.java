package net.carlosu.java.patterns.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Client {

	@Inject
	MessageFactory messageFactory;

	public void doMessage(){
		MessageType m = messageFactory.getMessage(Message.Type.SHORT);
		m.setMessage("This is a short message");
		System.out.println(m.getMessage());
		
		m = messageFactory.getMessage(Message.Type.LONG);
		m.setMessage("This is a long message");
		System.out.println(m.getMessage());
	}
}
