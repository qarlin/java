package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.tcontract.TContract;
import carlosu.batch.jobs.readers.InstructionItemReader;
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
	@Autowired
	private EntityManagerFactory entityManagerFactory1;
	
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
	
	@Test
	public void InstructionReaderTest() {
		List<Instruction> instructions = instructionService.findInstructions(bizDate.toDate());
		ItemReader<Instruction> reader = new IteratorItemReader<Instruction>(instructions);
		
		InstructionItemReader ir = new InstructionItemReader();
		ir.setItemReader(reader);
		ir.setContractService(contractService);
	}
	
	@Test
	public void HibernateItemReaderTest() throws UnexpectedInputException, ParseException, Exception{
		JpaPagingItemReader<Instruction> reader = new JpaPagingItemReader<Instruction>();
		reader.setEntityManagerFactory(entityManagerFactory1);
		reader.setQueryString("SELECT i FROM Instruction i WHERE i.bizDay = :bizday");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizday", bizDate.toDate());
		reader.setParameterValues(map);
		reader.setPageSize(100);
		
		int counter = 0;
		ExecutionContext executionContext = new ExecutionContext();
		reader.open(executionContext);
		Instruction instruction = new Instruction();
		
		while(instruction != null){
			instruction = reader.read();
			if (instruction != null)
				counter++;
		}
		reader.close();
		assertEquals(3, counter);
	}
	
	@Test
	public void ServiceItemReader() throws Exception{
		// The .read() get the collection, in the processor I have to process each element.
		ItemReaderAdapter<List<Instruction>> reader = new ItemReaderAdapter<List<Instruction>>();
		reader.setTargetObject(instructionService);
		reader.setTargetMethod("findInstructions");
		reader.setArguments(new Object[] {bizDate.toDate()});
		
		List<Instruction> list = reader.read();
		assertEquals(3, list.size());
	}
}
