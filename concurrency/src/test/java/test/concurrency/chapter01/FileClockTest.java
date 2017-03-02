package test.concurrency.chapter01;

import java.util.concurrent.TimeUnit;

import concurrency.chapter01.FileClock;

public class FileClockTest {

	public static void main(String... strings ) {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
	}

}
