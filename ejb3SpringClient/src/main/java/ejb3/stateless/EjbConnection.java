package ejb3.stateless;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbConnection {
	private String url;
	private String user;
	private String factory;
	private String password;
	private InitialContext initialContext;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public InitialContext getInitialContext() throws NamingException {
		if (initialContext == null){
			Properties env = new Properties();
			env.put(Context.PROVIDER_URL, url);
			env.put(Context.SECURITY_PRINCIPAL, user);
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.INITIAL_CONTEXT_FACTORY, factory);
			env.put("jboss.naming.client.ejb.context", true);
			
			initialContext = new InitialContext(env);
		}
		return initialContext;
	}
	public void setInitialContext(InitialContext initialContext) {
		this.initialContext = initialContext;
	}
	
}
