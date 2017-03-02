package test.concurrency.chapter01;

import org.junit.Test;

import concurrency.chapter01.PrimeGenerator;

public class PrimeGeneratorTest {

	@Test
	public void test() {
		Thread task = new PrimeGenerator();
		task.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}

}
