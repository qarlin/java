package jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import test.jdbc.conf.DataSourceTestConfig;

@Configuration
@ComponentScan({"jdbc"})
@PropertySource("classpath:/etc/${env:dev}/ds.properties")
@Import({ DataSourceConfig.class, DataSourceTestConfig.class })
public class AppConfiguration {
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
	
	@Bean 
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(jdbcTemplate());
	}
}
