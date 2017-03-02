package jdbc.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import jdbc.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component
public class AccountByIdQuery extends MappingSqlQuery<Account> {
	
	@Autowired
	public AccountByIdQuery(DataSource dataSource) {
		super(dataSource, "select id,owner_name,balance,access_time,locked from account where id = ?");
		declareParameter(new SqlParameter(Types.BIGINT));
        compile();

	}

    @Override
    protected Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setOwnerName(rs.getString("owner_name"));
        account.setBalance(rs.getDouble("balance"));
        account.setAccessTime(rs.getTimestamp("access_time"));
        account.setLocked(rs.getBoolean("locked"));
        return account;
    }
}
	