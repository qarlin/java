package multithread.ejb;

import java.util.Random;

public class EJBConnection {
	private String url;
	private String user;
	private String factory;
	private String password;
	private Object connection;
	
	public Object getInitialContext(){
		try {
			Random rand = new Random();
			int randomNum = rand.nextInt((5000) + 1);
			Thread.sleep(randomNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (connection == null)
			connection = new Object();
		return connection;
	}

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
	
	
}
