package remote;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.carlosu.wildfly.boundary.TheatreInfoRemote;

import org.junit.Before;
import org.junit.Test;

public class RemoteClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void JNDIFileTest() throws NamingException {
		Context ctx = new InitialContext();  
		TheatreInfoRemote remote = (TheatreInfoRemote) ctx.lookup("wildfly-example-ejb/TheatreInfo!net.carlosu.wildfly.boundary.TheatreInfoRemote");
		assertNotNull(remote);
		remote.printSeatList();
	}
	
	@Test
	public void lookupTest() throws NamingException {
		Properties jndiProps = new Properties();  
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");  
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbuser");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		
		// create a context passing these properties  
		Context ctx = new InitialContext(jndiProps);  
		// lookup  
		TheatreInfoRemote remote = (TheatreInfoRemote) ctx.lookup("wildfly-example-ejb/TheatreInfo!net.carlosu.wildfly.boundary.TheatreInfoRemote");
		assertNotNull(remote);
		
		remote.printSeatList();
	}
}
