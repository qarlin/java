package jdbc.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import jdbc.dao.AccountDao;
import jdbc.exceptions.DeleteFailedException;
import jdbc.exceptions.InsertFailedException;
import jdbc.exceptions.UpdateFailedException;
import jdbc.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoJdbcImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountByIdQuery accountByIdQuery;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("accountInsert")
    private SqlUpdate accountInsert;
    @Autowired
    @Qualifier("accountUpdate")
	private SqlUpdate accountUpdate;
    @Autowired
    @Qualifier("accountDelete")
	private SqlUpdate accountDelete;
	
    public void insert(Account account) {
    	GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		int count = accountInsert.update(new Object[]{account.getOwnerName(),account.getBalance(),account.getAccessTime(),account.isLocked()},keyHolder);
		if (count != 1)
			throw new InsertFailedException("Cannot insert account");
		account.setId(keyHolder.getKey().longValue());
	}

	public void update(Account account) {
		int count = accountUpdate.update(account.getOwnerName(),account.getBalance(),account.getAccessTime(),account.isLocked(),account.getId());
		if (count != 1)
			throw new UpdateFailedException("Cannot update account");
	}

	public void update(final List<Account> accounts) {
		int[] counts = jdbcTemplate.batchUpdate(
				"update account set (owner_name,balance,access_time,locked) = (?,?,?,?) where id = ?",
				new BatchPreparedStatementSetter() {        
				        public void setValues(PreparedStatement ps, int i) throws SQLException {
				            Account account = accounts.get(i);
				            ps.setString(1, account.getOwnerName());
				            ps.setDouble(2, account.getBalance());
				            ps.setTimestamp(3, new Timestamp(account.getAccessTime().getTime()));
				            ps.setBoolean(4, account.isLocked());
				            ps.setLong(5, account.getId());
				        }
				        
				        public int getBatchSize() {
				            return accounts.size();
				        }
				    });
				    int i = 0;
				    for(int count:counts) {
				        if(count == 0) throw new RuntimeException("Row not updated :" + i);
				        i++;
				    }


	}

	public void delete(long accountId) {
		int count = accountDelete.update(accountId);
		if (count != 1)
			throw new DeleteFailedException("Cannot delete account");
	}

	public Account find(long accountId) {
		return accountByIdQuery.findObject(accountId);
	}

	public List<Account> find(List<Long> accountIds) {
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"accountIds", accountIds);
		return namedParameterJdbcTemplate.query(
				"select * from account where id in (:accountIds)",
				sqlParameterSource, new AccountRowMapper());
	}

	public List<Account> find(String ownerName) {
		return namedParameterJdbcTemplate
				.query("select id,owner_name,balance,access_time,locked from account where owner_name = :ownerName",
						Collections.singletonMap("ownerName", ownerName),
						new AccountRowMapper());
	}

	public List<Account> find(final boolean locked) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"select * from account where locked = ?",
				new int[] { Types.BOOLEAN });
		return jdbcTemplate.query(psCreatorFactory
				.newPreparedStatementCreator(new Object[] { locked }),
				new AccountRowMapper());

	}
    
	@Component
	@Qualifier("accountInsert")
	public class AccountInsert extends SqlUpdate {
		@Autowired
		public AccountInsert(DataSource dataSource) {
	        super(dataSource,
	        "insert into account(owner_name,balance,access_time,locked) values(?,?,?,?)");
	        setParameters(new SqlParameter[] { 
	                new SqlParameter(Types.VARCHAR),
	                new SqlParameter(Types.DOUBLE),
	                new SqlParameter(Types.TIMESTAMP),
	                new SqlParameter(Types.BOOLEAN) });
	        setReturnGeneratedKeys(true);
	        setGeneratedKeysColumnNames(new String[]{"id"});
	        compile();
	    }
	}
	
	@Component
	@Qualifier("accountUpdate")
	public class AccountUpdate extends SqlUpdate {
		@Autowired
		public AccountUpdate(DataSource dataSource) {
	        super(dataSource, 
	        "update account set (owner_name,balance,access_time,locked) = (?,?,?,?) where id=?");
	        setParameters(new SqlParameter[] { 
	                new SqlParameter(Types.VARCHAR),
	                new SqlParameter(Types.DOUBLE),
	                new SqlParameter(Types.TIMESTAMP),
	                new SqlParameter(Types.BOOLEAN),
	                new SqlParameter(Types.BIGINT)});
	        compile();
	    }
	}
	
	@Component
	@Qualifier("accountDelete")
	public class AccountDelete extends SqlUpdate {
		@Autowired
		public AccountDelete(DataSource dataSource) {
	        super(dataSource, "delete account where id = ?");
	        setParameters(new SqlParameter[]{new SqlParameter(Types.BIGINT)});
	        compile();
	    }
	}
}
