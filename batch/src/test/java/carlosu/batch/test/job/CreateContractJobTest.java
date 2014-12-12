package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:spring/batch-createcontract-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateContractJobTest {

	@Autowired
	@Qualifier(value = "CreateContractJob")
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void launchJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals("Hello World!", outContent.toString());
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
 }
