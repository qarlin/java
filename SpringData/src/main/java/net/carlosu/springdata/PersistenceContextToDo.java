package net.carlosu.springdata;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories("net.carlosu.springdata.todo")
public class PersistenceContextToDo extends PersistenceContext{

	@Override
	protected String getPackage() {
		return "net.carlosu.springdata.todo";
	}

}
