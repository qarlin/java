package net.carlosu.java.patterns;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.carlosu.java.patterns.factoryspring.ProcessFactory;
import net.carlosu.java.patterns.factoryspring.ProcessType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-factory-config.xml"})
public class FactoryTest {
	@Inject
	ProcessFactory processFactory;
	
	@Test
	public void test() {
		ProcessType pt = processFactory.getProcess("shortProcess");
		assertEquals("Short", pt.processMessage());
	}

}
