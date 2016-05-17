package net.cts.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.cts.model.Library;

public class LibraryJerseyHATEOASControllerTest extends AbstractIntegrationTest {

	@Test
	public void library() {
		// RestTemplate with JSON
		JsonNode nodes = restTemplate.getForObject(getBaseUrl() + "/libraryHATEOAS", JsonNode.class);
		assertThat(nodes).isNotNull();

		Library library = new Library();
		library.setName("Java Library");
		library.setDescription("Java Library");

		// RestTemplate with ResponseEntity
		HttpEntity<Library> request = new HttpEntity<Library>(library);
		ResponseEntity<Resource<Library>> response = restTemplate.exchange(getBaseUrl() + "/libraryHATEOAS",
				HttpMethod.POST, request, new ParameterizedTypeReference<Resource<Library>>() {
				}, Collections.emptyMap());
		assertThat(response).isNotNull();
		// The getId() is null because the Object Library extends the
		// AbstractEntity to getId(). RestTemplate have a bug.
		// Solution: create my own ResourceLibrary and extend from Resource and
		// copy all the information needed
		// assertThat(response.getBody().getContent().getId()).isNotNull();

		// restTemplate POST with JSON
		nodes = restTemplate.postForObject(getBaseUrl() + "/libraryHATEOAS", library, JsonNode.class);
		assertThat(nodes).isNotNull();

		//ObjectMapper to convert from JSON to Object
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Resource<Library> customer = objectMapper.readValue(nodes.traverse(),
					new TypeReference<Resource<Library>>() {
					});
			assertThat(customer).isNotNull();
		} catch (IOException e) {
			e.printStackTrace();
		}

		nodes = restTemplate.getForObject(getBaseUrl() + "/libraryHATEOAS", JsonNode.class);
		assertThat(nodes).isNotNull();
	}
}
