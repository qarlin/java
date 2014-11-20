package net.carlosu.spring.mail;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailTestUtils {
	private static final Logger log = LogManager.getLogger(MailTestUtils.class);


    public static void reconfigureMailSenders(ApplicationContext applicationContext, String host, int port) {
        Map<String, JavaMailSenderImpl> ofType =
            applicationContext.getBeansOfType(org.springframework.mail.javamail.JavaMailSenderImpl.class);

        for (Map.Entry<String, JavaMailSenderImpl> bean : ofType.entrySet()) {
            log.info(String.format("configuring mailsender %s to use local Wiser SMTP", bean.getKey()));
            JavaMailSenderImpl mailSender = bean.getValue();
            mailSender.setHost(host);
            mailSender.setPort(port);
        }
    }
}
