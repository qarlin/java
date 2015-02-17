package multithread.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class Client2EjbService {
	@Autowired
	private ApplicationContext appContext;
	
	public Object getInitialContext(){
		EJBConnection client1EjbConnection = (EJBConnection) appContext.getBean("Client2Pool");
		return client1EjbConnection.toString() + " - " + client1EjbConnection.getInitialContext().toString();
	}
}
