package test.concurrency.chapter01;

import org.junit.Test;

import concurrency.chapter01.Calculator;

public class CalculatorTest {

	@Test
	public void test() {
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
