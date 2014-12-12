package carlosu.batch.test.job;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import carlosu.batch.domain.contract.Instruction;
import carlosu.batch.domain.contract.SourceReport;
import carlosu.batch.test.common.DBConnectionTest;

@ContextConfiguration(locations = {"classpath*:spring/batch-test.xml"})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class, 
        DBConnectionTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class InstructionJobTest {
	@Autowired
	@Qualifier(value = "instructionJob")
	private Job instructionJob;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JpaPagingItemReader<Instruction> instructionItemReader;
    @Autowired
    private StaxEventItemWriter<SourceReport> writer;
    
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    
    private static final String bizDay = "2014-01-01";
	LocalDate bizDate;
	
	@Before
	public void setUp() throws Exception {
		bizDate = LocalDate.parse(bizDay);
	}

	@Test
	public void launchTestJob(){
		JobParameters jobParameters = new JobParametersBuilder()
			.addDate("bizDay", bizDate.toDate()).toJobParameters();

	    JobExecution exec = jobLauncherTestUtils.launchStep("step1", jobParameters);
	    assertEquals(BatchStatus.COMPLETED, exec.getStatus());

	}
	@Test
	public void JPAItemReaderTest() throws UnexpectedInputException, ParseException, Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizday", bizDate.toDate());
		instructionItemReader.setParameterValues(map);
		
		int counter = 0;
		ExecutionContext executionContext = new ExecutionContext();
		instructionItemReader.open(executionContext);
		Instruction instruction = new Instruction();
		
		while(instruction != null){
			instruction = (Instruction) instructionItemReader.read();
			if (instruction != null)
				counter++;
		}
		instructionItemReader.close();
		assertEquals(3, counter);
	}
}
