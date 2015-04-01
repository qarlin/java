package net.carlosu.java.patterns;

import org.apache.log4j.Logger;

public class ApplicationLog {
	private static Logger appLogger = Logger.getLogger(ApplicationLog.class);
	
	public static void audit(String string){
		appLogger.info("Test in second file.");
	}
}
