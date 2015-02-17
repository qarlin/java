package multithread.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Client3EjbService {
	@Autowired
	@Qualifier("Client2Thread")
	private EJBConnection client1EjbConnection;

	public Object getInitialContext(){
		return client1EjbConnection.toString() + " - " + client1EjbConnection.getInitialContext().toString();
	}
}
