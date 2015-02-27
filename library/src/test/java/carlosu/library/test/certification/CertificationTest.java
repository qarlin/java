package carlosu.library.test.certification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CertificationTest {

	public static int sum(int i1, int i2) { return i1 + i2; }
	
	@SuppressWarnings("static-access")
	@Test
	public void NoNullPointerTest(){
		CertificationTest abc = null;
		assertEquals(abc.sum(5, 10), 15); // no NPE
		
		Integer xxx = 1;
		int xx = xxx.parseInt("123");
		assertEquals(xx, 123); 
	}
}
