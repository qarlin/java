package test.concurrency.chapter01;

import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import concurrency.chapter01.DataSourceLoader;
import concurrency.chapter01.NetworkConnectionLoader;

public class LoaderTest {
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String... strings ) {
		Thread thread1 = new Thread(new DataSourceLoader(), "DataSourceThread");
		Thread thread2 = new Thread(new NetworkConnectionLoader(), "NetworkConnectionThread");
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.printf(Level.TRACE, "Main has been loaded: %s", new Date());
	}

}
