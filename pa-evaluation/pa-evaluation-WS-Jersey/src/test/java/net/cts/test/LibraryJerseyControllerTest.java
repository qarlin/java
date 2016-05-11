package net.cts.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import net.cts.model.Library;

public class LibraryJerseyControllerTest extends AbstractIntegrationTest {

	@Test
	public void library() {
		List<Library> librariesFromMethod = getList("/library");
		assertThat(librariesFromMethod.isEmpty());

		Library library = new Library();
		library.setName("Java Library");
		library.setDescription("Java Library");

		Library libraryFromMethod = postEntity("/library", Library.class, library);
		assertThat(libraryFromMethod.getName()).isEqualTo(library.getName());

		librariesFromMethod = getList("/library");
		assertThat(librariesFromMethod.size()).isEqualTo(1);
	}
}
