package jaxws.soa.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/Hello")
public interface HelloRestService {

	@GET
	public String sayHello(String name);
}
