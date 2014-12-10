package carlosu.batch.test.job;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.services.ContractService;
import carlosu.batch.services.InstructionService;
import carlosu.batch.test.common.DBConnectionTest;

@ContextConfiguration(locations = {"classpath*:spring/batch-createcontract-context.xml"})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class, 
        DBConnectionTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BatchServiceTest {
	@Autowired
	private ContractService contractService;
	@Autowired
	private InstructionService instructionService;
	
	private static final String bizDay = "2014-01-01";
	LocalDate bizDate;
	
	@Before
	public void setUp() throws Exception {
		bizDate = LocalDate.parse(bizDay);
	}
	
	@Test
	public void InstructionServiceTest() {
		List<Instruction> instructions = instructionService.findInstructions(bizDate.toDate());
		assertEquals(3, instructions.size());
	}
	
	@Test
	public void ContractServiceTest() {
		String date = "RDV20140101%";
		List<TContract> contracts = contractService.findContracts(date);
		assertEquals(2, contracts.size());
	}

	@Test
	public void ContractServiceByExternalIdTest() {
		List<TContract> contracts = contractService.findContract("RDV2014010100001");
		assertEquals(1, contracts.size());
	}
}
