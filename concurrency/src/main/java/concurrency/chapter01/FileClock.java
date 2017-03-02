package concurrency.chapter01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileClock implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			logger.printf(Level.TRACE, "%s", new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				logger.printf(Level.TRACE, "the FileClock has been interrupted");
				return;
			}
		}
	}
}
