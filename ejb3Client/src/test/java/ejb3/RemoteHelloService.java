package ejb3;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ejb3.stateless.HelloServiceRemote;

public class RemoteHelloService {
	private InitialContext initialContext;
	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.SECURITY_PRINCIPAL, "");
		prop.put(Context.SECURITY_CREDENTIALS, "");
		prop.put("jboss.naming.client.ejb.context", true);
		initialContext = new InitialContext(prop);
	}

	@Test
	public void InitialContexttest() {
		assertNotNull(initialContext);
	}
	
	@Test
	public void EJbConnectionTest() throws NamingException{
		
		final String beanName = "HelloServiceRemote";
		final String viewClassName = HelloServiceRemote.class.getName();
		
		String ejbName = "ejb3/" + beanName + "!" + viewClassName;
		//String ejbName = "ejb3/HelloServiceRemote!ejb3.stateless.HelloServiceRemote";
		HelloServiceRemote ejb = (HelloServiceRemote) initialContext.lookup(ejbName);
		assertNotNull(ejb);
		
		assertEquals("Hello", ejb.sayHello());
	}

}
