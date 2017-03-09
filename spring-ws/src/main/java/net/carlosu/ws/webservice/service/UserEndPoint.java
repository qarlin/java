package net.carlosu.ws.webservice.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import net.carlosu.ws.webservice.domain.UserDetailsResponse;
import net.carlosu.ws.webservice.domain.UserRequest;

@Endpoint
public class UserEndPoint {
	public static final String NAMESPACE = "http://localhost:8080/ws/model";
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "UserRequest")
	@ResponsePayload
	public UserDetailsResponse getUserDetails(@RequestPayload UserRequest userRequest) {
		UserDetailsResponse userDetails = null;
		if ("lubos.krnac@gmail.com".equals(userRequest.getEmail())) {
			userDetails = new UserDetailsResponse();
			userDetails.setFirstName("Lubos");
			userDetails.setLastName("Krnac");
		}
		return userDetails;
	}
}
