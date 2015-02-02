package jaxws.service.customer;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
@Produces(MediaType.APPLICATION_XML)
public class CustomerRestService {
 
    @GET
    @Path("{login: [a-z]*}")
    public Response getCustomerByLogin(@PathParam("login") String login) {
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com", "1234565");
        customer.setLogin(login);
        return Response.ok(customer).build();
    }
 
    @GET
    @Path("{customerId : \\d+}")
    public Response getCustomerById(@PathParam("customerId") Long id) {
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com", "1234565");
        customer.setId(id);
        return Response.ok(customer).build();
    }
 
    @GET
    public Response getCustomersByZipCode(@QueryParam("zip") Long zip) {
        Customers customers = new Customers();
        customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
        customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
        return Response.ok(customers).build();
    }
 
    @GET
    @Path("search")
    public Response getCustomerByName(@MatrixParam("firstname") String firstname, @MatrixParam("surname") String surname) {
        Customers customers = new Customers();
        customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
        customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
        return Response.ok(customers).build();
    }
}
