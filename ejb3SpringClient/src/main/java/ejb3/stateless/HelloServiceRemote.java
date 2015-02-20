package ejb3.stateless;

import javax.ejb.Remote;

@Remote
public interface HelloServiceRemote {
	public String sayHello();
}
