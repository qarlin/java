package net.carlosu.spring.mail;

import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:mail-context.xml" })
public class mailTest extends AbstractJUnit4SpringContextTests {
	private Wiser wiser;
	@Autowired
	MailService mailService;

	@Before
	public void setup() {
		wiser = new Wiser();
		wiser.setHostname("localhost");
		wiser.setPort(2500);
		wiser.start();

		MailTestUtils.reconfigureMailSenders(applicationContext, "localhost",
				2500);
	}

	@Test
	public void doTaskSuccess() throws Exception {

		mailService.sendPreConfiguredMail("Message 01");
		Assert.assertEquals(wiser.getMessages().size(), 1);

		if (wiser.getMessages().size() > 0) {
			WiserMessage wMsg = wiser.getMessages().get(0);
			MimeMessage msg = wMsg.getMimeMessage();

			Assert.assertNotNull(msg);
			Assert.assertEquals(msg.getContent(), "Message 01");
		}
	}

	@After
	public void after() {
		wiser.stop();
	}
}
