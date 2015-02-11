package jaxws.soa.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8080/hello/HelloWSService")
public interface HelloWSService {

	@WebMethod
	public String sayHello(String name);
}
