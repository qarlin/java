package carlosu.library.test.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListTest {

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("serial")
	@Test
	public void testArrayList(){
		List<String> list = new ArrayList<String>(){{
			   add("A");
			   add("B");
			}};
		assertEquals(2, list.size());;
	}

}
