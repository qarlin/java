package carlosu.batch.test.ddl;

import java.io.FileOutputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:spring/ddl-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DDLSpringTest {
	@Autowired
	private DataSource datasource;
	
	@Before
	public void setUp() throws Exception {
		Connection con = datasource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(con);
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable("contract");
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset_temp.xml"));
	}

	@Test
	public void test() {
	}

}
