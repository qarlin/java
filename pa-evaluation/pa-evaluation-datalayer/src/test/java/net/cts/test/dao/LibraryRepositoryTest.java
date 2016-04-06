package net.cts.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.cts.JPAAplication;
import net.cts.model.Library;
import net.cts.repository.LibraryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JPAAplication.class)
public class LibraryRepositoryTest {
	@Autowired
    private LibraryRepository repository;
	
	@Test
	public void test() {
		Library library = new Library();
		library.setName("Java");
		library.setDescription("Java Library");
		
		repository.save(library);
		
		List<Library> libraries = repository.findAll();
		assertEquals(1, libraries.size());
	}

}
