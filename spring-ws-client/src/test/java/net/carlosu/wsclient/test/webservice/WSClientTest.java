package net.carlosu.wsclient.test.webservice;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreators;

import net.carlosu.ws.client.WebServiceClient;
import net.carlosu.ws.client.WsClientConfiguration;
import net.carlosu.ws.client.model.UserDetailsResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WsClientConfiguration.class)
public class WSClientTest {
	@Autowired
	private WebServiceClient client;
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@Test
	public void test() throws IOException {
        MockWebServiceServer mockServer = MockWebServiceServer.createServer(webServiceTemplate);
        
        mockServer
                .expect(RequestMatchers.payload(new ClassPathResource("testRequest.xml")))
                .andRespond(ResponseCreators.withPayload(new ClassPathResource("testResponse.xml")));

		UserDetailsResponse userResponse =  client.getUserDetails("lubos.krnac@gmail.com");
		assertNotNull(userResponse);
		assertEquals(userResponse.getFirstName(), "Lubos");
		assertEquals(userResponse.getLastName(), "Krnac");
		
        mockServer.verify();
	}
}
