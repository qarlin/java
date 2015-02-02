package jaxws.service.test.customer;

import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jaxws.service.customer.Customer;
import jaxws.service.customer.Customers;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.plugins.providers.jsonp.JsonObjectProvider;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CustomerRestServiceIT {
	// ======================================
	// = Attributes =
	// ======================================
	private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><customer><email>jsmith@gmail.com</email><firstName>John</firstName><lastName>Smith</lastName><phoneNumber>1234565</phoneNumber></customer>";
	private static final String URL = "http://localhost:8080/jaxws/rs/customer";
	
	@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jaxws.war")
        		.addPackages(true, "jaxws.service", "jaxws.service.customer")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	// ======================================
	// = Tests =
	// ======================================
	@Test
	@RunAsClient
	public void shouldMarshallACustomer() throws JAXBException {
		// given
		Customer customer = new Customer("John", "Smith", "jsmith@gmail.com",
				"1234565");
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Customer.class);
		Marshaller m = context.createMarshaller();
		m.marshal(customer, writer);
		// then
		assertEquals(XML, writer.toString());
	}

	@Test
	@RunAsClient
	public void shouldMarshallAListOfCustomers() throws JAXBException {
		Customers books = new Customers();
		books.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
		books.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565"));
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Customers.class);
		Marshaller m = context.createMarshaller();
		m.marshal(books, writer);
	}

	@Test
	// @Ignore
	public void shouldCheckURIs() throws IOException {
		Client client = ClientBuilder.newClient();
		// Valid URIs
		Response response = getResource(URL + "/agoncal", APPLICATION_XML_TYPE);//client.target(URL + "/agoncal").request().get();
		assertEquals(200, response.getStatus());
		Customer customer = response.readEntity(Customer.class);
		assertNotNull(customer);
		response.close();
		
		response = client.target(URL + "/1234").request().get();
		assertEquals(200, response.getStatus());
		response.close();
		
		response = client.target(URL + "?zip=75012").request().get();
		assertEquals(200, response.getStatus());
		response.close();
		
		response = client.target(URL + "/search;firstname=Antonio;surname=Goncalves").request().get();
		assertEquals(200, response.getStatus());
		response.close();
		
		// Invalid URIs
		response = client.target(URL + "/AGONCAL").request().get();
		assertEquals(404, response.getStatus());
		response.close();
		
		response = client.target(URL + "/dummy/1234").request().get();
		assertEquals(404, response.getStatus());
		response.close();
	}

	@Test
	@RunAsClient
	// @Ignore
	public void shouldGetCustomerByLogin() throws IOException {
		Client client = ClientBuilder.newClient();
		// Valid URIs
		Response response = client
				.target(URL + "/agoncal").request()
				.get();
		assertEquals(200, response.getStatus());
		System.out.println("###############################");
		System.out.println(response.readEntity(String.class));
	}

	@Test
	// @Ignore
	public void shouldGetCustomers() throws IOException {

		Client client = ClientBuilder.newClient();
		// Valid URIs
		Response response = client.target(URL + "")
				.request().get();
		assertEquals(200, response.getStatus());
		System.out.println("###############################");
		System.out.println(response.readEntity(String.class));
	}
	
	private Response getResource(String url, MediaType mediaType) {
        System.out.println("URL: " + url);
        System.out.println("MediaType: " + mediaType.toString());

        // Using the JAX-RS client, initiate a request
        // using the url as the target
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .register(JsonObjectProvider.class)
                .request(mediaType)
                .get();

        // Check the HTTP status of the request
        // HTTP 200 indicates the request is OK
        assertEquals(200, response.getStatus());

        return response;
    }
}