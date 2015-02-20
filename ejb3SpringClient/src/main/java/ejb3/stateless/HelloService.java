package ejb3.stateless;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HelloService {
	@Autowired
	@Qualifier("ejbConnThread")
	private EjbConnection ejbConn;
	
	public void execute(){
		try {
			Random rand = new Random();
			Thread.sleep(rand.nextInt(5001));
			System.out.printf("Current thread %s - ", Thread.currentThread().toString());
			System.out.println(ejbConn.getInitialContext());
		} catch (Exception e) {
			// TODO: handle exception
		} 
	}
}
