package test.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import jdbc.AppConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,
	classes={AppConfiguration.class})
@ActiveProfiles("dev")
public class DataSourceDevTest {

	@Autowired
	@Qualifier("dataSource")
	private DataSource datasource;
	
	@Autowired
	@Qualifier("pooledDataSource")
	private DataSource pooledDatasource;
	
	@Test
	public void connection() throws SQLException {
		assertNotNull(datasource);
		Connection connection = datasource.getConnection();
		assertFalse(connection.isClosed());
		connection.close();
		assertTrue(connection.isClosed());
	}
	
	@Test
	public void pooledConnection() throws SQLException {
		assertNotNull(pooledDatasource);
		Connection connection = pooledDatasource.getConnection();
		assertFalse(connection.isClosed());
		connection.close();
		assertTrue(connection.isClosed());
	}

}
