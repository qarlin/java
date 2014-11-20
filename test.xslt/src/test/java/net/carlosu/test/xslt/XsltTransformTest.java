package net.carlosu.test.xslt;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;

public class XsltTransformTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		InputStream input = ClassLoader.getSystemResourceAsStream("styler.xsl");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\"?>");
		sb.append("<hello-world><greeter>An XSLT Programmer</greeter><greeting>Hello, World!</greeting></hello-world>");
		StringReader reader = new StringReader(sb.toString());

	    StringWriter writer = new StringWriter();
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer(new StreamSource(input));

	    transformer.transform(new StreamSource(reader), new StreamResult(writer));

	    String result = writer.toString();
	    System.out.println(result);
	}

}
