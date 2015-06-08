package simpleconfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@SpringBootApplication
@IntegrationComponentScan
public class Application {

	@SuppressWarnings("resource")
	public static void main(String... args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"context.xml");
		// Simple Service
		TempConverter converter = ctx.getBean("simpleGateway",
				TempConverter.class);
		System.out.println(converter.fahrenheitToCelcius(68.0f));
		// Web Service
		converter = ctx.getBean("wsGateway", TempConverter.class);
		System.out.println(converter.fahrenheitToCelcius(68.0f));
		
	}

}
