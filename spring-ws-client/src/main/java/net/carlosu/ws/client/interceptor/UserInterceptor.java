package net.carlosu.ws.client.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

@Component
public class UserInterceptor implements ClientInterceptor {
	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		System.out.println("Client handleRequest");
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		System.out.println("Client handleResponse");
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		System.out.println("Client handleFault");
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
		System.out.println("Client afterCompletion");
	}
}
