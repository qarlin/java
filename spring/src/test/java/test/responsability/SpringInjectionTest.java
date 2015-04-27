package test.responsability;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chainresponsability.printer.Printer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:chain-config.xml"})
public class SpringInjectionTest {
    @Autowired
    List<Printer> list;
 
    @Autowired
    Set<Printer> set;
 
    @Autowired
    Map<String, Printer> map;
    
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(list.size() == 5);
		assertTrue(set.size() == 5);
		assertTrue(map.size() == 5);
	}

}
