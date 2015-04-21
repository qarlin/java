package ejbremote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import process.SpringConfigurationTest;
import ejb.ContractEJBRemote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfigurationTest.class)
public class SpringJavaConfigTest {
	@Autowired
	private Context context;
	@Autowired
	@Qualifier("EJBService")
	private Context EJBService;
	
	@Test
	public void test() throws NamingException {
		assertNotNull(context);
		
		ContractEJBRemote remote = (ContractEJBRemote) context.lookup("batch-process-ejb/ContractEJB!ejb.ContractEJBRemote");
		assertEquals("Done", remote.test());
	}
	
	@Test
	public void LocalthreadTest() throws NamingException {
		assertNotNull(EJBService);
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					ContractEJBRemote localRemote;
					try {
						localRemote = (ContractEJBRemote) EJBService.lookup("batch-process-ejb/ContractEJB!ejb.ContractEJBRemote");
						System.out.println(EJBService.toString() + " " + localRemote.test());
					} catch (NamingException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		executorService.shutdown();
		while(!executorService.isTerminated()){}
	}
}
