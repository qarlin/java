package concurrency;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleLog4j2 {
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String... args){
		String name = "CarlosU";
		Calendar date = new GregorianCalendar(2015, 06, 01);
		
		logger.info("Test Log {}", name);
		logger.printf(Level.TRACE, "Logging in user %s with birthday %s", name, date);
		logger.printf(Level.TRACE, "Logging in user %1$s with birthday %2$tm %2$te,%2$tY", name, date);
		logger.printf(Level.DEBUG, "Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
		logger.printf(Level.DEBUG, "Long.MAX_VALUE = %,d", Long.MAX_VALUE);
		
	}
}
