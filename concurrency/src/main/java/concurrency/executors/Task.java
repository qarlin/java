package concurrency.executors;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Runnable doesn't return a value
public class Task implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	private Date initDate;
	private String name;
	
	public Task(String name){
		this.initDate = new Date();
		this.name = name;
	}
	
	@Override
	public void run() {
		logger.printf(Level.INFO, "%s: Task %s: created on: %s\n", Thread.currentThread().getName(), name, initDate);
		logger.printf(Level.INFO, "%s: Task %s: created on: %s\n", Thread.currentThread().getName(), name, new Date());
		try {
			Long duration = (long)(Math.random()*10);
			logger.printf(Level.INFO, "%s: Task %s: Doing task during %d seconds \n", Thread.currentThread().getName(), name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		logger.printf(Level.INFO, "%s: Task %s: created on: %s\n", Thread.currentThread().getName(), name, new Date());
	}

}
