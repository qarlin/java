package net.carlosu.jms.test;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Message;
import javax.jms.Queue;

import net.carlosu.jms.JMSMessagingUtil;
import net.carlosu.jms.mdb.CounterPartyPollerMDB;
import net.carlosu.jms.mdb.LocalEngineMDB;
import net.carlosu.jms.model.CustomerMessage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CounterPartyPollerTest {

	private static final Logger logger = Logger.getLogger(CounterPartyPollerTest.class);
	
	@EJB (mappedName = "java:module/JMSMessagingUtil")
    private JMSMessagingUtil jmsUtil;
	
	@Resource (mappedName = "queue/CounterPartyPoller")
    private Queue queue;
	
	@Resource (mappedName = "queue/FileProcessor")
    private Queue replyQueue;
	
	@Deployment
    public static Archive<?> getDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(JMSMessagingUtil.class)
                .addClass(CounterPartyPollerMDB.class)
                .addClass(LocalEngineMDB.class)
                .addClass(CustomerMessage.class)
                .addAsWebInfResource("META-INF/hornetq-jms.xml");
		logger.info(war.toString(true));
        return war;
    }

	@Test
    public void testDDBasedMDB() throws Exception {
		this.jmsUtil.sendTextMessage("Say hello to ", this.queue, this.replyQueue);
        final Message reply = this.jmsUtil.receiveMessage(replyQueue, 5000);
        Assert.assertNotNull("Reply message was null on reply queue: " + this.replyQueue, reply);
    }
}
