package net.carlosu.springdata.test.todo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.carlosu.springdata.todo.model.Todo;
import net.carlosu.springdata.todo.repository.TodoRepository;

public class TodoRepositoryIntegrationTest extends AbstractIntegrationTest{
	@Autowired
	private TodoRepository repository;

	@Test
	public void getAllTodo() {
		List<Todo> list = repository.findAll();
		assertThat(list, is(notNullValue()));
	}
	
	@Test
	public void findBy(){
		List<Todo> list = repository.findBySearchTermNamed("25");
		assertThat(list.size(), is(1));
		
		list = repository.findBySearchTermNamedNative("26");
		assertThat(list.size(), is(1));
	}
}
