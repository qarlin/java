package test.factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import factory.model.Document;
import factory.strategy.PrintStrategyFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SpringFactoryPatternTest{ 
	
	@Autowired
	private PrintStrategyFactory printStrategyFactory;
	
	@Test
	public void printStrategyFactoryTest(){
		Document doc = new Document();
		
		printStrategyFactory.getStrategy("DEFAULT").print(doc);
		printStrategyFactory.getStrategy("A5L").print(doc);
		printStrategyFactory.getStrategy("A5P").print(doc);
		printStrategyFactory.getStrategy("A5Portrait").print(doc);
	}
}
