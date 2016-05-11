package net.cts.test;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.cts.model.Library;

public class LibraryControllerTest extends AbstractIntegrationTest {

	@Test
	public void library() {
		List<Library> librariesFromMethod = getRESTList("/library", new HashMap<String, String>());
		assertThat(librariesFromMethod.isEmpty());

		Library library = new Library();
		library.setName("Java Library");
		library.setDescription("Java Library");

		Library libraryFromPostMethod = postRESTEntity("/library", Library.class, library);
		assertThat(libraryFromPostMethod.getName()).isEqualTo(library.getName());

		librariesFromMethod = getRESTList("/library", new HashMap<String, String>());
		assertThat(librariesFromMethod.size()).isEqualTo(1);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		Library libraryFromGetMethod = getRESTEntity("/library/{id}", Library.class, map);
		assertThat(libraryFromGetMethod.getName()).isEqualTo(library.getName());
		
		libraryFromPostMethod.setName("Java Library v2");
		Library libraryFromPutMethod = putRESTEntity("/library/{id}", Library.class, libraryFromPostMethod, map);
		assertThat(libraryFromPutMethod.getName()).isEqualTo(libraryFromPostMethod.getName());
		
		deleteRESTEntity("/library/{id}", map);
		librariesFromMethod = getRESTList("/library", new HashMap<String, String>());
		assertThat(librariesFromMethod.isEmpty());
	}
}
