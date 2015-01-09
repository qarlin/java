package carlosu.batch.test.common;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DBConnectionTest extends AbstractTestExecutionListener{
		
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
    	executeScript(testContext, "dataSource1", DatabaseOperation.CLEAN_INSERT, "init-script-db1.xml");
		executeScript(testContext, "dataSource2", DatabaseOperation.CLEAN_INSERT, "init-script-db2.xml");
    }
    
    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
		executeScript(testContext, "dataSource1", DatabaseOperation.DELETE_ALL, "init-script-db1.xml");
		executeScript(testContext, "dataSource2", DatabaseOperation.DELETE_ALL, "init-script-db2.xml");
    }
	
	private void executeScript(TestContext testContext, String dsId, DatabaseOperation operation, String script) throws Exception{
		DataSource dataSource = (DataSource) testContext.getApplicationContext().getBean(dsId);
    	
		Connection con = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(con);
		operation.execute(connection, getDataSet(script));
		
		DataSourceUtils.releaseConnection(con, dataSource);
	}
	
	private IDataSet getDataSet(String file) throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		InputStream is = ClassLoader.getSystemResourceAsStream(file);
		IDataSet dataSet = builder.build(is);
		return dataSet;
	}
	

}
