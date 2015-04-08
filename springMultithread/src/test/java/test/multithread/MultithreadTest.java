package test.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class MultithreadTest {

	@Test
	public void executorTest() throws InterruptedException{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 10; i++) {
			executor.execute(new MyThread("String" + i));
		}
		
		executor.shutdown();
		System.out.println("-----------------------");
		// wait until all tasks are finished
		while (!executor.isTerminated()) {
        }
		System.out.println("All tasks are finished!");
	}
}
