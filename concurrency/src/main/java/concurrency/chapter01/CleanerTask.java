package concurrency.chapter01;

import java.util.Date;
import java.util.Deque;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CleanerTask extends Thread {
	private static final Logger logger = LogManager.getLogger();
	
	private Deque<Event> deque;
	
	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		setDaemon(true);
	}
	
	@Override
	public void run(){
		while (true){
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;
		
		if (deque.size() == 0)
			return;
		
		delete = false;
		
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000){
				logger.printf(Level.TRACE, "Cleaner: %s", e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		
		if (delete)
			logger.printf(Level.TRACE, "Cleaner: Size of the queue: %d", deque.size());
	}
}
