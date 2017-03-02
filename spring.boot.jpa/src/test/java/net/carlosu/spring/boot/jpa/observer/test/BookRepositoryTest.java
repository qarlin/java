package net.carlosu.spring.boot.jpa.observer.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.carlosu.spring.boot.jpa.observer.domain.Book;
import net.carlosu.spring.boot.jpa.observer.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class BookRepositoryTest {
	@Autowired
	BookRepository repository;
	
	@Test
	public void executeQuery(){
		List<Book> books = repository.findAll();
		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(10);
	}
}
