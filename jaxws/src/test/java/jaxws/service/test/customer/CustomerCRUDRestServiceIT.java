package jaxws.service.test.customer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jaxws.service.customer.Customer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CustomerCRUDRestServiceIT {
	// ======================================
	// = Attributes =
	// ======================================
	private static final String URL = "http://localhost:8080/jaxws/rs/customer";
	private static Client client = ClientBuilder.newClient();

	@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jaxws.war")
            .addPackage("jaxws.service")
            .addPackage("jaxws.service.customer")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	// ======================================
	// = Unit tests =
	// ======================================
	@Test
	public void shouldMarshallACustomer() throws JAXBException {
		// given
		Customer customer = new Customer("John", "Smith", "jsmith@gmail.com",
				"1234565");
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Customer.class);
		Marshaller m = context.createMarshaller();
		m.marshal(customer, writer);
	}

	@Test
	public void shouldCheckGetCustomerByLoginResponse() {
		Response response = client
				.target(URL + "/agoncal").request()
				.get();
		assertEquals(200, response.getStatus());
		response.close();
	}

	@Test
	public void shouldCheckGetCustomerByLogin() {
		String login = "agoncal";
		Customer customer = client.target(URL + "/agoncal")
				.request().get(Customer.class);
		
		assertEquals(login, customer.getLogin());
	}

	@Test
	public void shouldCheckGetCustomerByIdResponse() {
		Response response = client
				.target(URL + "/1234").request().get();
		assertEquals(200, response.getStatus());
		response.close();
	}

	@Test
	public void shouldCheckGetCustomerById() throws Exception {
		String id = "1234";
		Customer customer = client.target(URL)
				.path(id).request().get(Customer.class);
		
		assertEquals(id, customer.getId().toString());
	}

	@Test
	public void shouldCheckGetCustomerByZipCodeURI() {
		Response response = client
				.target(URL + "?zip=75012").request()
				.get();
		assertEquals(200, response.getStatus());
		response.close();
	}

	@Test
	public void shouldCheckGetCustomerByZipCodeWithParamURI() {
		Response response = client.target(URL + "")
				.queryParam("zip", 75011L).request().get();
		assertEquals(200, response.getStatus());
		response.close();
	}

	@Test
	public void shouldCheckGetCustomerByFirstnameNameURI() {
		Response response = client
				.target(URL + "/search;firstname=Antonio;surname=Goncalves")
				.request().get();
		assertEquals(200, response.getStatus());
		response.close();
	}

	@Test
	public void shouldCheckGetCustomerByFirstnameNameWithParamURI() {
		Response response = client
				.target(URL + "/search")
				.matrixParam("firstname", "Antonio2")
				.matrixParam("surname", "Goncalves2").request().get();
		assertEquals(200, response.getStatus());
		response.close();
		
	}

	@Test
	@Ignore
	public void shouldCheckGetCustomerWithCookieParamURI() {
		Cookie myCookie = new Cookie("myCookie", "This is my cookie");
		client = ClientBuilder.newClient();
		String response = client
				.target(URL + "/cookie").request()
				.cookie(myCookie).get(String.class);
		assertEquals("This is my cookie from the server", response);
	}

	@Test
	@Ignore
	public void shouldEchoUserAgentValue() {
		client = ClientBuilder.newClient();
		String response = client
				.target(URL + "/userAgent").request()
				.get(String.class);
		assertEquals(
				"Jersey/2.0-m09 (HttpUrlConnection 1.7.0_04) from the server",
				response);
	}
}
