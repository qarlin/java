package jaxws.soa.services;

import javax.ejb.Stateful;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateful
@WebService(serviceName = "HelloWSService", portName = "HelloWorld", name = "HelloWorld", endpointInterface = "jaxws.soa.services.HelloWSService", targetNamespace = "http://localhost:8080/hello/HelloWSService")
@Path("/Hello")
public class HelloServiceImpl implements HelloEJBService, HelloRestService,
		HelloWSService {

	@GET
	public String sayHello(String name){
		return "Hello " + name;
	}
}
