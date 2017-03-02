package jdbc.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.model.Account;

import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int arg1) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("id"));
		account.setOwnerName(rs.getString("owner_name"));
		account.setBalance(rs.getDouble("balance"));
		account.setAccessTime(rs.getTimestamp("access_time"));
		account.setLocked(rs.getBoolean("locked"));
		return account;
	}
}
