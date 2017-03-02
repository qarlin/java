package test.concurrency.chapter01;

import java.util.ArrayDeque;
import java.util.Deque;

import concurrency.chapter01.CleanerTask;
import concurrency.chapter01.Event;
import concurrency.chapter01.WriterTask;

public class DequeTest {

	public static void main(String... strings ) {
		Deque<Event> deque = new ArrayDeque<Event>();
		
		WriterTask writer = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
		
		try {
			cleaner.join(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
