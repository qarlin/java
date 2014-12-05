package carlosu.batch.test.job;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateContractJobTest.class, CreateContractTaskletTest.class,
		InstructionItemReaderMockTest.class })
public class AllTests {

}
