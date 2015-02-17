package test.multithread.ejb;

import static org.junit.Assert.assertNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import multithread.ejb.EjbService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class EjbMultithread {

	@Autowired
	private ApplicationContext appContext;
	
	@Test
	public void ContractSingletonConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Singleton Object - contract1EjbService");
		System.out.println("----------------");
		
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					@SuppressWarnings("unchecked")
					EjbService<String, String> contract1EjbService = (EjbService<String, String>) appContext.getBean("contract1EjbService");
					String response = contract1EjbService.send("", null);
					assertNull(response);
					System.out.println(contract1EjbService.getInitialContext());
				}
			});
		}
		executorService.shutdown();
		while (!executorService.isTerminated()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void ContractPrototypeConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Pool Object - contract2EjbService");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					@SuppressWarnings("unchecked")
					EjbService<String, String> contract2EjbService = (EjbService<String, String>) appContext.getBean("contract2EjbService");
					String response = contract2EjbService.send("", null);
					assertNull(response);
					System.out.println(contract2EjbService.getInitialContext());
				}
			});
		}
		executorService.shutdown();
		while (!executorService.isTerminated()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void ContractThreadConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Thread Object - contract3EjbService");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					@SuppressWarnings("unchecked")
					EjbService<String, String> contract3EjbService = (EjbService<String, String>) appContext.getBean("contract3EjbService");
					String response = contract3EjbService.send("", null);
					assertNull(response);
					System.out.println(contract3EjbService.getInitialContext());
				}
			});
		}
		executorService.shutdown();
		while (!executorService.isTerminated()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Autowired
	@Qualifier("contract3EjbService")
	private EjbService<String, String> contract3EjbService;
	
	@Test
	public void ContractThreadConnectionAutowired() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Thread Object - contract3EjbService Autowired");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					String response = contract3EjbService.send("", null);
					assertNull(response);
					System.out.println(contract3EjbService.getInitialContext());
				}
			});
		}
		executorService.shutdown();
		while (!executorService.isTerminated()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
