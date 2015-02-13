package test.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.example4.Component4;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/config4.xml"})
public class Test4{
	@Autowired
	private Component4 component;

	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testReadProperty(){
		String expectedValue = "Message from prop1.properties" +
				"\nMessage from prop2.properties" +
				"\nMessage from prop3.properties";
		
		Assert.assertEquals(component.getMessage(), expectedValue);
	}
}
