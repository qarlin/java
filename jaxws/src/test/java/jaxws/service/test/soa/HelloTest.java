package jaxws.service.test.soa;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import jaxws.service.ApplicationConfig;
import jaxws.soa.services.HelloEJBService;
import jaxws.soa.services.HelloWSService;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloTest {

	@EJB
	HelloEJBService helloEjbService;

	private HelloWSService helloWSService;
	
	@Deployment
    public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "hello.war")
			.addPackage(HelloEJBService.class.getPackage())
			.addClass(ApplicationConfig.class)
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "WEB-INF/web.xml");
    }
	
	@Test
	public void HelloEJBTest() {
		String message = helloEjbService.sayHello("John");
		Assert.assertEquals("The EJB is not working", "Hello John", message);
	}

	@Test
	public void HelloWSTest() throws MalformedURLException {
		QName serviceName = new QName("http://localhost:8080/hello/HelloWSService", "HelloWSService");
		Service service = Service.create(new URL("http://localhost:8080/hello/HelloWSService?wsdl"), serviceName);
		helloWSService = service.getPort(HelloWSService.class);
		Assert.assertNotNull(helloWSService);
		Assert.assertEquals("The WS is not working", "Hello John", helloWSService.sayHello("John"));
	}
	
	@Test
	public void HelloRestTest() {
		String url = "http://localhost:8080/jaxws/rs/Hello";
		Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request()
                .get();
        assertEquals(200, response.getStatus());
	}
}
