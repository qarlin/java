package net.cts.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.cts.WSJerseyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WSJerseyApplication.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractIntegrationTest {

	protected TestRestTemplate restTemplate = new TestRestTemplate();
	@Value("${local.server.port}")
	private int port;

	private String getBaseUrl() {
		return "http://localhost:" + port;
	}

	protected <T> T getEntity(final String uri) {
		final ResponseEntity<T> entity = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<T>(){});
		return entity.getBody();
	}
	
	protected <T> List<T> getList(String uri){
    	ResponseEntity<List<T>> entity = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<T>>(){});
    	return entity.getBody();
    }
    
    protected <T> T postEntity(String uri, Class<T> serviceReturnTypeClass, T objectToPost){
    	HttpEntity<T> request = new HttpEntity<T>(objectToPost);
        ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.POST, request, serviceReturnTypeClass);
        return response.getBody();
    }
}
