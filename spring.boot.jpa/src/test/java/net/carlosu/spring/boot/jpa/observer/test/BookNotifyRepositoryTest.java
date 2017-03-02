package net.carlosu.spring.boot.jpa.observer.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import net.carlosu.spring.boot.jpa.observer.domain.Book;
import net.carlosu.spring.boot.jpa.observer.repository.BookNotifyRepository;
import scala.annotation.meta.setter;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class BookNotifyRepositoryTest {
	@Autowired
	BookNotifyRepository repository;
	
	@Test
	public void updateTest(){
		List<Book> books = repository.findAll();
		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(10);
		
		Optional<Book> book = repository.findOne(new Long(1));
		assertThat(book.get()).isNotNull();
		
		Book b = book.get();
		b.setPrice("300");
		b = repository.save(b);
		assertThat(b.getPrice()).isEqualTo("300");
	}
}
