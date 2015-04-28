package carlosu.library.test.certification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CertificationTest {

	public static int sum(int i1, int i2) { return i1 + i2; }
	
	public CertificationTest(int opt) {
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void NoNullPointerTest(){
		CertificationTest abc = null;
		assertEquals(abc.sum(5, 10), 15); // no NPE
		
		Integer xxx = 1;
		int xx = xxx.parseInt("123");
		assertEquals(xx, 123); 
		
		double x = Double.NEGATIVE_INFINITY;
		double y = Double.POSITIVE_INFINITY;
		System.out.print(x-y);
	}
	
	@Test
	public void ConstructionTest(){
		// Compile error
		//CertificationTest test = new CertificationTest();
	}
	
	@Test
	public void operatorTest(){
		int x = 6;
		long y = 8;
		long z = x + y;
		
		short a = 5;
		short b = 7;
		int c = a + b;
	}
}
