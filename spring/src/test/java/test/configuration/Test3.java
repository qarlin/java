package test.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.example3.Component3;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/config3.xml"})
public class Test3{

	@Autowired
	private Component3 component;
	
	@Test
	public void testReadProperty(){
		Assert.assertEquals(component.getMessage(), "Message from prop3.properties");
	}
}
