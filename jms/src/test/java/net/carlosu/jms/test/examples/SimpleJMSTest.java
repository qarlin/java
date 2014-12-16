package net.carlosu.jms.test.examples;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import net.carlosu.jms.test.examples.mdb.ReplyingMDB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SimpleJMSTest {
	@Resource(mappedName = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	
	@Resource(mappedName = "queue/test")
	private Queue queue;
	
	@Deployment
    public static Archive<?> deployment() {
        return ShrinkWrap.create(WebArchive.class)
        		.addClass(ReplyingMDB.class)
                .addAsWebInfResource("META-INF/hornetq-jms.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Test
	public void test() {
		Connection connection = null; 
		try {	
			connection = connectionFactory.createConnection(); 
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue replyDestination = session.createTemporaryQueue();
			
			TextMessage message = session.createTextMessage();
			message.setText("This is message from producer"); 
			message.setJMSReplyTo(replyDestination);
			
			MessageProducer producer = session.createProducer(queue);
			
			producer.send(message);
		    producer.close();
		    
		    MessageConsumer consumer = session.createConsumer(replyDestination);
		    Message replyMsg = consumer.receive(5000);
		    Assert.assertNotNull("Reply message was null on reply queue: " + replyDestination, replyMsg);
		    
		} catch (Exception e){
			Assert.fail();
		} finally { 
	        if (connection != null) { 
	            try { 
	            	connection.close(); 
	            } 
	            catch (JMSException e) { } 
	        }
	    }
		
	}
	
	

}
