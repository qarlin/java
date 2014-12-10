package carlosu.batch.test.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.repository.jpa.InstructionJPADao;
import carlosu.batch.repository.jpa.TContractJPADao;
import carlosu.batch.test.common.DBConnectionTest;

@ContextConfiguration(locations = { "classpath*:spring/batch-jpa-context.xml" })
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class, 
        DBConnectionTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaConnectionTest {

	@Autowired
	private InstructionJPADao instructionDao;
	
	@Autowired
	private TContractJPADao tcontractDao;
	
	@Test
	public void InstructionTest() {
		List<Instruction> instructions = instructionDao.findAll();
		assertEquals(4, instructions.size());
	}
	
	@Test
	public void TContractTest() {
		List<TContract> contracts = tcontractDao.findAll();
		assertEquals(2, contracts.size());
	}
	
	@Test
	public void TContractAndMessagesTest() {
		List<TContract> contracts = tcontractDao.findAllContracts();
		assertEquals(2, contracts.size());
	}
}
