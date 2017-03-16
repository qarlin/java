package net.carlosu.ws.rest.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class EndpointRegister extends ResourceConfig {
	public EndpointRegister() {
		super();
		register(UserResource.class);
	}
}
