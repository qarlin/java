package test.concurrency.chapter01;

import concurrency.chapter01.PrimeGenerator;

public class PrimeGeneratorTest {

	public static void main(String... strings ) {
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
