package carlosu.batch.test.job;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class DBConnectionTest {

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
