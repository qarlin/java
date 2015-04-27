package net.carlosu.java.patterns;

import static org.junit.Assert.*;
import net.carlosu.java.patterns.strategy.model.Trade;
import net.carlosu.java.patterns.strategy.spring.BookService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class StrategySpringTest {
	@Autowired
	private BookService service;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(service);
		
		Trade trade = new Trade();
		service.book(trade);
		
		
	}

}
