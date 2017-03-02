package test.concurrency.chapter01;

import concurrency.chapter01.Calculator;

public class CalculatorTest {

	public static void main(String... strings ) {
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
