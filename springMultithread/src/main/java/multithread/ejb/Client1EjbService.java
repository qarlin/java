package multithread.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Client1EjbService{
	@Autowired
	@Qualifier("Client1")
	private EJBConnection client1EjbConnection;

	public Object getInitialContext(){
		return client1EjbConnection.toString() + " - " + client1EjbConnection.getInitialContext().toString();
	}
}
