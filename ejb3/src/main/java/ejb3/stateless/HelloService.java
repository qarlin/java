package ejb3.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name="HelloServiceRemote")
@Remote(HelloServiceRemote.class)
public class HelloService{
	public String sayHello(){
		return "Hello";
	}
}
