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
	public void test() throws NamingException {
		final Properties jndiProperties = new Properties();
		jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(jndiProperties); 
		
		TheatreInfoRemote remote = (TheatreInfoRemote) ctx.lookup("ejb:/wildfly-example-ejb/TheatreInfo!net.carlosu.wildfly.boundary.TheatreInfoRemote");
		assertNotNull(remote);
		remote.printSeatList();
	}

}
