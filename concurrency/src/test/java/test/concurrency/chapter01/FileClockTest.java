package test.concurrency.chapter01;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import concurrency.chapter01.FileClock;

public class FileClockTest {

	@Test
	public void test() {
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
