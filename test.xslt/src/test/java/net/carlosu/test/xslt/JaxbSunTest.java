package net.carlosu.test.xslt;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

public class JaxbSunTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JAXBException, IOException {
		Person p = new Person();
		p.setName("J");
		
		StringWriter writer = new StringWriter();
    	JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(p, writer);
		
		System.out.println(writer.toString());
		writer.close();
	}

}
