package concurrency.chapter01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkConnectionLoader implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void run() {
		logger.printf(Level.TRACE, "Beginning network connection loading: %s", new Date());
		
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.printf(Level.TRACE, "Network connection loading has finished: %s", new Date());
	}

}
