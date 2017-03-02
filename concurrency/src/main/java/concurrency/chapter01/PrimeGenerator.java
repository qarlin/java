package concurrency.chapter01;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeGenerator extends Thread {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void run(){
		long number = 1L;
		while (true) {
			if (isPrime(number))
				logger.printf(Level.TRACE,"Number %d is Prime", number);
			
			if (isInterrupted()){
				logger.printf(Level.TRACE,"The Prime Generator has been interrupted");
				return;
			}
			number++;
		}
	}
	
	private boolean isPrime(long number){
		if (number <=2) {
			return true;
		}
		for (long i=2; i < number; i++){
			if ((number % i) == 0)
				return false;
		}
		return true;
	}
}
