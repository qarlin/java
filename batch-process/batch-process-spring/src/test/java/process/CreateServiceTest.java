package process;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Contract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ejbprocess.dao.ContractDAO;
import ejbprocess.factory.ServiceFactory;
import ejbprocess.service.BatchService;
@ContextConfiguration(classes = SpringConfigurationTest.class)
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
		
		service.setContractDAO(getContractDTO());
		service.execute(context);
		System.out.print(true);
	}
	
	private ContractDAO getContractDTO() {
		ContractDAO contractDAO = new ContractDAO() {
			
			@Override
			public List<Contract> getInstructions(Date date) {
				List<Contract> contracts = new ArrayList<Contract>();
				for (int i = 0; i < 3; i++) {
					Contract c = new Contract();
					c.setId(i);
					c.setName("Name" + i);
					contracts.add(c);
				}
				return contracts;
			}
		};
		
		return contractDAO;
	}
	

}
