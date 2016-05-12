package net.cts.test;

import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

	protected <T> T getRESTEntity(final String uri, Class<T> serviceReturnTypeClass, Map<String, String> map) {
		ResponseEntity<T> entity = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.GET, HttpEntity.EMPTY, serviceReturnTypeClass, map);
		return entity.getBody();
	}
	
	protected <T> List<T> getRESTList(String uri, Map<String, String> map){
    	ResponseEntity<List<T>> entity = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<T>>(){}, map);
    	return entity.getBody();
    }
    
	protected <T> Page<T> getRESTPage(String uri, Map<String, String> map){
    	ResponseEntity<RestResponsePage<T>> entity = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.GET, null, new ParameterizedTypeReference<RestResponsePage<T>>(){});
    	return entity.getBody();
    }
	
    protected <T> T postRESTEntity(String uri, Class<T> serviceReturnTypeClass, T objectToPost){
    	HttpEntity<T> request = new HttpEntity<T>(objectToPost);
        ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.POST, request, serviceReturnTypeClass);
        return response.getBody();
    }
    
    protected <T> T putRESTEntity(String uri, Class<T> serviceReturnTypeClass, T objectToPost, Map<String, String> map){
    	HttpEntity<T> request = new HttpEntity<T>(objectToPost);
        ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + uri, HttpMethod.PUT, request, serviceReturnTypeClass, map);
        return response.getBody();
    }
    
    protected <T> void deleteRESTEntity(String uri, Map<String, String> map){
    	restTemplate.delete(getBaseUrl() + uri, map);
    }
}
