package net.carlosu.ws.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.core.SoapFaultMessageResolver;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import net.carlosu.ws.client.interceptor.UserInterceptor;

@Configuration
@ComponentScan
public class WsClientConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("net.carlosu.ws.client.model");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller, UserInterceptor userInterceptor) {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);
		webServiceTemplate.setDefaultUri("http://localhost:8080/ws/");
		
		ClientInterceptor[] interceptors = new ClientInterceptor[] { userInterceptor };
		webServiceTemplate.setInterceptors(interceptors);

		webServiceTemplate.setFaultMessageResolver(new SoapFaultMessageResolver());
	    
		/*HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
		messageSender.setConnectionTimeout(100);
		messageSender.setMaxTotalConnections(10);
		webServiceTemplate.setMessageSender(messageSender);*/
		return webServiceTemplate;
	}
}
