package process;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ejbprocess.factory.ServiceFactory;
import ejbprocess.service.BatchService;
@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateServiceTest {
	@Autowired
	private ServiceFactory factory;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String instType = "CREATE";
		Date bizDay = new Date();
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("type", instType);
		context.put("bizday", bizDay);
		
		BatchService service = factory.getService(instType);
		assertNotNull(service);
		
		service.execute(context);
	}

}
