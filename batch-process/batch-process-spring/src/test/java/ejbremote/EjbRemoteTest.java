package ejbremote;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.ContractDTO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ejb.ContractEJBRemote;

@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EjbRemoteTest {
	//@Autowired
	//private ContractEJBRemote remoteObject;
	@Autowired
	private JndiTemplate jndiRemote;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ejbRemoteTest() throws NamingException {
		Properties jndiProps = new Properties();  
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");  
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbuser");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.HEARTBEAT_INTERVAL", 1000);
		
		
		// create a context passing these properties  
		Context ctx = new InitialContext(jndiProps);  
		// lookup  
		ContractEJBRemote remote = (ContractEJBRemote) ctx.lookup("batch-process-ejb/ContractEJB!ejb.ContractEJBRemote");
		assertNotNull(remote);
		
		assertEquals("Done", remote.test());
		
		ContractDTO dto = new ContractDTO();
		dto.setId(1);
		dto.setName("Test1");
		List<ContractDTO> list = new ArrayList<ContractDTO>();
		list.add(dto);
		assertNotNull(remote.create(list));
	}
	
	@Test
	@Ignore
	public void SpringEjbRemoteTest() {
		//assertNotNull(remoteObject);
		//assertEquals("Done", remoteObject.test());
	}
	
	@Test
	@Ignore
	public void SpringEjbRemote2Test() throws IllegalArgumentException, NamingException {
		Properties jndiProps = new Properties();  
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");  
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbuser");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.HEARTBEAT_INTERVAL", 1000);
		
		JndiTemplate template = new JndiTemplate(jndiProps);
		
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiTemplate(template);
		bean.setJndiName("batch-process-ejb/ContractEJB!ejb.ContractEJBRemote");
		bean.afterPropertiesSet();
		
		ContractEJBRemote remote = (ContractEJBRemote) bean.getObject();
		
		ContractDTO dto = new ContractDTO();
		dto.setId(1);
		dto.setName("Test1");
		assertNotNull(remote.create(dto));
		
		assertNotNull(remote);
		assertEquals("Done", remote.test());
	}
	
	@Test
	public void SpringEjbRemote3Test() throws IllegalArgumentException, NamingException {
		assertNotNull(jndiRemote);
		
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiTemplate(jndiRemote);
		bean.setJndiName("batch-process-ejb/ContractEJB!ejb.ContractEJBRemote");
		bean.afterPropertiesSet();
		
		ContractEJBRemote remote = (ContractEJBRemote) bean.getObject();
		
		assertNotNull(remote);
		assertEquals("Done", remote.test());
	}
	
	@Autowired
	private JndiObjectFactoryBean bean;
	@Test
	public void SpringEjbRemote4Test() throws IllegalArgumentException, NamingException {
		assertNotNull(bean);
		
		bean.afterPropertiesSet();
		ContractEJBRemote remote = (ContractEJBRemote) bean.getObject();
		
		assertNotNull(remote);
		assertEquals("Done", remote.test());
	}

}
