package test.responsability;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import chainresponsability.PrinterChain;
import chainresponsability.model.User;

public class ChainResponsabilityTest {

	@Test
	public void test() {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chain-config.xml");
         PrinterChain printerChain = (PrinterChain) context.getBean("printerChain");
         printerChain.introduceUser(new User("Marco Castigliego", 'M'));
         printerChain.introduceUser(new User("Julie Marot", 'F'));
         context.close();
	}

}
