package concurrency.chapter01;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WriterTask implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	private Deque<Event> deque;
	
	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent(String.format("The thread %s has generated an event %d", Thread.currentThread().getId(), i));
			deque.addFirst(event);
			logger.printf(Level.TRACE, "Writer: %s", event.getEvent());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
