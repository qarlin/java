package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.ItemReader;

import carlosu.batch.domain.Contract;
import carlosu.batch.domain.Instruction;
import carlosu.batch.jobs.readers.InstructionItemReader;
import carlosu.batch.repository.InstructionDao;

public class InstructionItemReaderTest {
	private InstructionItemReader reader;
	@Mock
	private InstructionDao instructionDAO;
	@Mock
	private ItemReader<Instruction> instructionReader;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		reader = new InstructionItemReader();
		reader.setInstructionDAO(instructionDAO);
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
		instruction.setId(1L);
		instruction.setCommentId("CLS001");

        when(instructionReader.read()).thenReturn(instruction);
        
        when(instructionDAO.getContracts("CLS001")).thenReturn(
                new ArrayList<Contract>() {
                    {
                        add(new Contract());
                    }
                });

        Instruction instruction1 = reader.read();

        assertEquals(instruction.getId(), instruction1.getId());
        assertEquals(1, instruction1.getContracts().size());
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
