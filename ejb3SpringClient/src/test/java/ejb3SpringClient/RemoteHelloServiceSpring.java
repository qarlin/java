package ejb3SpringClient;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ejb3.stateless.EjbConnection;
import ejb3.stateless.HelloService;
import ejb3.stateless.HelloServiceRemote;

@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RemoteHelloServiceSpring {

	@Autowired
	@Qualifier("HelloService3")
	private HelloServiceRemote helloService3;
	
	@Autowired
	@Qualifier("ejbConn")
	private EjbConnection ejbConn;
	
	@Autowired
	@Qualifier("helloService")
	private HelloService helloService;
	
	@Test
	public void test() throws NamingException {
		//assertNotNull(helloService);
		//assertEquals("Hello", helloService.sayHello());
		
		assertNotNull(helloService);
		helloService.execute();
	}

	@Test
	public void PrototypeConnection(){
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		System.out.println("\n");
		System.out.println("Prototype - One Instance");
		System.out.println("========================");
		
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					
					helloService.execute();
				}
			});
		}
		
		executorService.shutdown();
		while (!executorService.isTerminated()){}
	}
}
