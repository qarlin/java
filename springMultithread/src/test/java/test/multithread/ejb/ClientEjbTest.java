package test.multithread.ejb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import multithread.ejb.EJBConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class ClientEjbTest {
	@Autowired
	private ApplicationContext appContext;
	
	@Test
	public void ClientSingletonConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Client Singleton Object");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					EJBConnection conn = (EJBConnection) appContext.getBean("Client1");
					System.out.println(conn.toString() + " --- " + conn.getInitialContext().toString());
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
	public void ClientPrototypeConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Client Prototype Object");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					EJBConnection conn = (EJBConnection) appContext.getBean("Client2");
					System.out.println(conn.toString() + " --- " + conn.getInitialContext().toString());
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
	public void ClientPoolConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Client Pool Object");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					EJBConnection conn = (EJBConnection) appContext.getBean("Client2Pool");
					System.out.println(conn.toString() + " --- " + conn.getInitialContext().toString());
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
	public void ClientThreadConnection() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println("Client Thread Object");
		System.out.println("----------------");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					EJBConnection conn = (EJBConnection) appContext.getBean("Client2Thread");
					System.out.println(conn.toString() + " --- " + conn.getInitialContext().toString());
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
