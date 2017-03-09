package net.carlosu.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import net.carlosu.ws.client.model.UserDetailsResponse;
import net.carlosu.ws.client.model.UserRequest;

@Component
public class WebServiceClientSoap {
	private WebServiceTemplate webServiceTemplate;

	private static final SoapActionCallback messageCallback = new SoapActionCallback("getUserDetails");

	@Autowired
	public WebServiceClientSoap(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	public UserDetailsResponse getUserDetails(String email) {
		UserRequest request = new UserRequest();
		request.setEmail(email);

		UserDetailsResponse userDetails = (UserDetailsResponse) webServiceTemplate.marshalSendAndReceive(request,
				messageCallback);
		return userDetails;
	}
}
