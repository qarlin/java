package com.cgi.db.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger Logger = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        Logger.info("Message");
    	System.out.println( "Hello World!" );
    }
}
