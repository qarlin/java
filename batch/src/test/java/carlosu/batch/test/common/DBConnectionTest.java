package carlosu.batch.test.common;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
public class DBConnectionTest extends AbstractTestExecutionListener{

	private DataSource dataSource;
	
	@Override
    public void afterTestClass(TestContext testContext) throws Exception {
		dataSource = (DataSource) testContext.getApplicationContext().getBean("dataSource1");
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet()); 
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
    	dataSource = (DataSource) testContext.getApplicationContext().getBean("dataSource1");
    	DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet()); 
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
