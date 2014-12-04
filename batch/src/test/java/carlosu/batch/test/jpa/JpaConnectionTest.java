package carlosu.batch.test.jpa;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import carlosu.dao.InstructionJPADao;
import carlosu.model.Instruction;

@ContextConfiguration(locations = { "classpath*:spring/batch-jpa-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaConnectionTest {

	@Autowired
	private InstructionJPADao instructionDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet()); 
	}

	@After  
    public void after() throws Exception{  
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());  
    }  
	
	@Test
	public void test() {
		List<Instruction> instructions = instructionDao.findAll();
		assertTrue(instructions.size() == 3);
	}

	private IDataSet getDataSet() throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		InputStream is = ClassLoader.getSystemResourceAsStream("init-script.xml");
		IDataSet dataSet = builder.build(is);
		return dataSet;
	}

	private IDatabaseConnection getConnection() throws Exception {
		Connection con = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(con);
		return connection;
	}
}
