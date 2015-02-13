package test.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.example2.Component2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/config2.xml"})
public class Test2{
	@Autowired
	private Component2 component;
	
	@Test
	public void testReadProperty(){
		Assert.assertEquals(component.getMessage(), "Message from prop2.properties");
	}
}
