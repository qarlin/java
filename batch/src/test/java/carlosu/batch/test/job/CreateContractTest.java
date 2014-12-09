package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;

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

import carlosu.batch.domain.contract.Contract;
import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.repository.jpa.InstructionJPADao;
import carlosu.batch.test.common.DBConnectionTest;

@ContextConfiguration(locations = {"classpath*:spring/batch-createcontract-context.xml"})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class, 
        DBConnectionTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateContractTest{
	@Autowired
	private InstructionJPADao instructionDao;
	
	private static final String bizDay = "2014-01-01";
	LocalDate bizDate;
	
	@Before
	public void setUp() throws Exception {
		bizDate = LocalDate.parse(bizDay);
	}

	@Test
	public void testGetAllValidInstructions(){
		List<Instruction> instructions = instructionDao.getInstructions(bizDate.toDate());
		assertEquals(2, instructions.size());
	}
	
	@Test
	public void testInstructionsAndContracts(){
		List<Contract>	contracts = instructionDao.getContracts(bizDate.toDate());
		assertEquals(1, contracts.size());
		
		List<Instruction> instructions = instructionDao.getAllInstructions(bizDate.toDate());
		List<Instruction> allInstructions = instructionDao.findAll();
		assertEquals(4, allInstructions.size());
		assertEquals(3, instructions.size());
	}

}
