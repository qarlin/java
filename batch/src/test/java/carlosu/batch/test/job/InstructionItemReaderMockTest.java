package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.ItemReader;

import carlosu.batch.domain.contract.Contract;
import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.jobs.readers.InstructionItemReader;
import carlosu.batch.repository.InstructionDao;

public class InstructionItemReaderMockTest {
	private InstructionItemReader reader;
	@Mock
	private InstructionDao instructionDAO;
	@Mock
	private ItemReader<Instruction> instructionReader;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		reader = new InstructionItemReader();
		reader.setItemReader(instructionReader);
	}

	@Test
	public void test() {
		assertNotNull(reader);
		assertNotNull(instructionDAO);
		assertNotNull(instructionReader);
	}

	@Test
	public void testReadNoItems() throws Exception {
		assertNull(reader.read());
	}
	
	@SuppressWarnings("serial")
	@Test
    public void testReadWithCustomer() throws Exception {
		Instruction instruction = new Instruction();
		instruction.setInstructionId(1L);
		instruction.setCommentId("CLS001");

        when(instructionReader.read()).thenReturn(instruction);
        
        when(instructionDAO.getContracts("CLS001")).thenReturn(
                new ArrayList<Contract>() {
                    {
                        add(new Contract());
                    }
                });

        Instruction instruction1 = reader.read();

        assertEquals(instruction.getInstructionId(), instruction1.getInstructionId());
        assertEquals(1, instruction1.getContracts().size());
    }
}
