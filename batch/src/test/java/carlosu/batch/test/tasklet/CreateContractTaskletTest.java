package carlosu.batch.test.tasklet;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.step.tasklet.Tasklet;

import carlosu.batch.tasklets.CreateContractTasklet;

public class CreateContractTaskletTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Tasklet tasklet = new CreateContractTasklet();

	@Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecute() throws Exception {
        tasklet.execute(null, null);
        assertEquals("Hello World!", outContent.toString());
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}
