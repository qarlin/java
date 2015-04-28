package net.carlosu.java.patterns.factory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class MessageFactory {

	@Inject
	@Any
	private Instance<MessageType> messages;
	
	public MessageType getMessage(Message.Type type){
		MessageLiteral literal = new MessageLiteral(type);
		Instance<MessageType> typeMessage = messages.select(literal);
		return typeMessage.get();
	}
}
