package net.carlosu.jms.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import net.carlosu.jms.model.CustomerMessage;

/**
 * Message-Driven Bean implementation class for: CounterPartyPollerMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/CounterPartyPoller"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") }, mappedName = "queue/CounterPartyPoller")
public class CounterPartyPollerMDB implements MessageListener {
	@Resource(lookup = "java:/JmsXA")
    private ConnectionFactory factory;

    private Connection connection;
    private Session session;
    
	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage msg = (TextMessage) message;
				sendMessage(msg);
				System.out.println(msg.getText());
			} else if (message instanceof ObjectMessage){
				ObjectMessage msg = (ObjectMessage)message;
				CustomerMessage cmsg = (CustomerMessage)msg.getObject();
				sendMessage(msg);
				System.out.println(cmsg.getCustomer());
			}
		} catch (JMSException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
    protected void preDestroy() throws JMSException {
        session.close();
        connection.close();
    }

    @PostConstruct
    protected void postConstruct() throws JMSException {
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    
    private void sendMessage(Message message){
    	try {
            System.out.println("Message " + message);
            final Destination destination = message.getJMSReplyTo();
            // ignore messages that need no reply
            if (destination == null)
                return;
            final MessageProducer replyProducer = session.createProducer(destination);
            final Message replyMsg = session.createTextMessage("replying " + ((TextMessage) message).getText());
            replyMsg.setJMSCorrelationID(message.getJMSMessageID());
            replyProducer.send(replyMsg);
            replyProducer.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
