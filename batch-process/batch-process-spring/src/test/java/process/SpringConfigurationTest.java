package process;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-config.xml")
public class SpringConfigurationTest {

	@Bean
	public Context context() throws IllegalArgumentException, NamingException{
		Properties jndiProps = new Properties();  
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");  
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbuser");
		jndiProps.put("jboss.naming.client.ejb.context", true);
	
		Context context = new InitialContext(jndiProps);
		return context;
	}
	
	/*@Bean
	public LocalContext localContext() throws IllegalArgumentException, NamingException{
		Hashtable<String, String> jndiProps = new Hashtable<String, String>();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");  
		jndiProps.put(Context.SECURITY_CREDENTIALS, "ejbuser");
		jndiProps.put("jboss.naming.client.ejb.context", "true");
	
		LocalContext context = new LocalContext(jndiProps);
		return context;
	}
	
	@Bean
	public LocalContext threadContext() throws IllegalArgumentException, NamingException{

		ThreadLocalTargetSource source = new ThreadLocalTargetSource();
		source.setTargetClass(LocalContext.class);
		source.setTargetBeanName("localContext");
		
		ProxyFactoryBean proxy = new ProxyFactoryBean();
		proxy.setTarget(source);
		proxy.setTargetClass(LocalContext.class);
		
		return (LocalContext) proxy.getObject();
	}*/
}
