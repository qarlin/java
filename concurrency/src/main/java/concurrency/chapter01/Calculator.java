package concurrency.chapter01;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculator implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	private int number;
	
	public Calculator(int number) {
		this.number = number;	
	}
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			logger.printf(Level.TRACE, "%s: %d * %d = %d", Thread.currentThread().getName(), number, i, i*number);
		}

	}

}
