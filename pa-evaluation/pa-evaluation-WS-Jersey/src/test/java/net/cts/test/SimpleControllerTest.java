package net.cts.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import net.cts.WSJerseyApplication;
import net.cts.model.Library;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WSJerseyApplication.class)
@WebIntegrationTest(randomPort = true)
public class SimpleControllerTest {

    private RestTemplate restTemplate = new TestRestTemplate();
    @Value("${local.server.port}")
	private int port;

    private String getBaseUrl() {
		return "http://localhost:" + port;
	}
    
    @Test
    public void library() {
    	ResponseEntity<String> responseError = restTemplate.getForEntity(getBaseUrl() + "/error", String.class);
        assertThat(responseError.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        ResponseEntity<String> responseOk = restTemplate.getForEntity(getBaseUrl() + "/library", String.class);
        assertThat(responseOk.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        ResponseEntity<List<Library>> entity = restTemplate.exchange(getBaseUrl() + "/library", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Library>>(){});
    	List<Library> libraries = entity.getBody();
        assertThat(libraries.isEmpty());
        
        List<Library> librariesFromMethod = getList(getBaseUrl() + "/library");
        assertThat(librariesFromMethod.isEmpty());
        
        Library library = new Library();
        library.setName("Java Library");
        library.setDescription("Java Library");
        
        HttpEntity<Library> request = new HttpEntity<Library>(library);
        ResponseEntity<Library> response = restTemplate.exchange(getBaseUrl() + "/library", HttpMethod.POST, request, Library.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Library libraryResponse = response.getBody();
        assertThat(libraryResponse.getName()).isEqualTo(library.getName());
		
        librariesFromMethod = getList(getBaseUrl() + "/library");
        assertThat(librariesFromMethod.size()).isEqualTo(1);
        
        Library library2 = new Library();
        library2.setName("C++ Library");
        library2.setDescription("C++ Library");
        Library libraryFromMethod = postObject(getBaseUrl() + "/library", Library.class, library2);
        assertThat(libraryFromMethod.getName()).isEqualTo(library2.getName());
		
        librariesFromMethod = getList(getBaseUrl() + "/library");
        assertThat(librariesFromMethod.size()).isEqualTo(2);
    }
    
    protected <T> List<T> getList(String uri){
    	ResponseEntity<List<T>> entity = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<T>>(){});
    	return entity.getBody();
    }
    
    protected <T> T postObject(String uri, Class<T> serviceReturnTypeClass, T objectToPost){
    	HttpEntity<T> request = new HttpEntity<T>(objectToPost);
        ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.POST, request, serviceReturnTypeClass);
        return response.getBody();
    }
}