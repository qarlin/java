package concurrency.chapter01;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandler implements UncaughtExceptionHandler {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.printf(Level.TRACE, "An exception has been captured");
		logger.printf(Level.TRACE, "Thread: %s", t.getId());
		logger.printf(Level.TRACE, "Exception: %s: %s", e.getClass().getName(), e.getMessage());
		logger.printf(Level.TRACE, "Stack Trace:");
		//logger.printf(Level.TRACE, "%s", e.printStackTrace());
		logger.printf(Level.TRACE, "Thread status: %s", t.getState());
	}
}
