package test.concurrency;

import concurrency.Calculator;

public class CalculatorTest {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
