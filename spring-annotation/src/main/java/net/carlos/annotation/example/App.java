package net.carlos.annotation.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("appContext.xml");
		
		AppConfiguration appConfig = ac.getBean("AppConfiguration", AppConfiguration.class);
		System.out.println(appConfig.toString());
		
		((ClassPathXmlApplicationContext)ac).close();

	}

}
