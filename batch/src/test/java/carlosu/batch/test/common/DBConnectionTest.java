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

	@Override
    public void afterTestClass(TestContext testContext) throws Exception {
		DataSource dataSource1 = (DataSource) testContext.getApplicationContext().getBean("dataSource1");
		DataSource dataSource2 = (DataSource) testContext.getApplicationContext().getBean("dataSource2");
		DatabaseOperation.DELETE_ALL.execute(getConnection(dataSource1), getDataSet("init-script-db1.xml")); 
		DatabaseOperation.DELETE_ALL.execute(getConnection(dataSource2), getDataSet("init-script-db2.xml")); 
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
    	DataSource dataSource1 = (DataSource) testContext.getApplicationContext().getBean("dataSource1");
    	DataSource dataSource2 = (DataSource) testContext.getApplicationContext().getBean("dataSource2");
    	DatabaseOperation.CLEAN_INSERT.execute(getConnection(dataSource1), getDataSet("init-script-db1.xml"));
    	DatabaseOperation.CLEAN_INSERT.execute(getConnection(dataSource2), getDataSet("init-script-db2.xml")); 
    }
    
	private IDataSet getDataSet(String file) throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		InputStream is = ClassLoader.getSystemResourceAsStream(file);
		IDataSet dataSet = builder.build(is);
		return dataSet;
	}

	private IDatabaseConnection getConnection(DataSource ds) throws Exception {
		Connection con = ds.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(con);
		return connection;
	}
	

}
