package net.carlosu.ws.test.webservice;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseActions;
import org.springframework.ws.test.server.ResponseMatchers;

import net.carlosu.ws.webservice.WebServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebServiceConfig.class)
public class WSEndPointTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void test() throws IOException {
        MockWebServiceClient wsClient = MockWebServiceClient.createClient(applicationContext);
        
		RequestCreator requestCreator = RequestCreators.withPayload(new ClassPathResource("testRequest.xml"));

		// WHEN
		ResponseActions response = wsClient.sendRequest(requestCreator);

		// THEN
		response.andExpect(ResponseMatchers.noFault())
				.andExpect(ResponseMatchers.payload(new ClassPathResource("testResponse.xml")));

	}
}
