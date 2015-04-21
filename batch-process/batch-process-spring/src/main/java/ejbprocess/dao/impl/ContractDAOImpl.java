package ejbprocess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Contract;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import ejbprocess.dao.ContractDAO;

@Component
public class ContractDAOImpl implements ContractDAO{

	private JdbcTemplate contractJdbcTemplate;
	
	@Override
	public List<Contract> getInstructions(Date date) {
		String sql = "select * from instructions";
		Object[] params = new Object[] { date };
		List<Contract> result = contractJdbcTemplate.query(sql, params, new ResultSetExtractor<List<Contract>>() {

			
			public List<Contract> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Contract> list = new ArrayList<Contract>();
				while (rs.next()) {
					Contract contract = new Contract();
					contract.setId(rs.getInt(0));
					list.add(contract);
				}
				return list;
			}
			
		});
		return result;
	}

}
