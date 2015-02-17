package multithread.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Client2EjbService {
	@Autowired
	@Qualifier("Client2Pool")
	private EJBConnection client1EjbConnection;
	
	public Object getInitialContext(){
		//EJBConnection client1EjbConnection = (EJBConnection) appContext.getBean("Client2Pool");
		return client1EjbConnection.toString() + " - " + client1EjbConnection.getInitialContext().toString();
	}
}
