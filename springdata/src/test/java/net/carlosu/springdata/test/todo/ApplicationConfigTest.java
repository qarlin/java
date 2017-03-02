package net.carlosu.springdata.test.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.carlosu.springdata.PersistenceContext;
import net.carlosu.springdata.PersistenceContextToDo;
import net.carlosu.springdata.todo.repository.TodoRepository;

public class ApplicationConfigTest {
	@Test
	public void bootstrapAppFromJavaConfig() {

		ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceContextToDo.class);
		assertThat(context, is(notNullValue()));
		assertThat(context.getBean(TodoRepository.class), is(notNullValue()));
	}
}
