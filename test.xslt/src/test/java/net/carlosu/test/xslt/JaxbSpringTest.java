package net.carlosu.test.xslt;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class JaxbSpringTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	@Before
	public void setup() {
	
	}
	
	@Test
	public void convertXMLToObject() throws IOException{
		Person p = new Person();
		p.setName("J");
		
		StringWriter out = new StringWriter();
		marshaller.marshal(p, new StreamResult(out));
		System.out.println(out.toString());
		
		out.close();
	}
	
		
}
