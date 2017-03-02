package test.concurrency.chapter01;

import concurrency.chapter01.ExceptionHandler;
import concurrency.chapter01.Task;

public class ExceptionTest {

	public static void main(String... strings ){
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
