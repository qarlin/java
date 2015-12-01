package net.carlosu.springdata.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import net.carlosu.springdata.PersistenceContext;
import net.carlosu.springdata.todo.repository.TodoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,
classes={PersistenceContext.class})
public class ApplicationConfigJUnitTest {

	@Autowired
	private TodoRepository repository;
	
	@Test
	public void isNotNullRepository(){
		assertThat(repository, is(notNullValue()));
	}
}
