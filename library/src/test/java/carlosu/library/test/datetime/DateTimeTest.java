package carlosu.library.test.datetime;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class DateTimeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseDate(){
		LocalDate fromDate = LocalDate.parse("2014-01-01");
		String sDate = fromDate.toString();
		assertEquals(sDate, "2014-01-01");
	}
}
