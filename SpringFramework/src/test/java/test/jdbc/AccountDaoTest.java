package test.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jdbc.dao.AccountDao;
import jdbc.model.Account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import test.jdbc.conf.DataSourceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,
	classes={DataSourceTestConfig.class})
public class AccountDaoTest {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private AccountDao accountDao;

	@Test
	public void tablesAndDataImportedTest(){
		assertNotNull(accountDao);
		
		List<Account> result = accountDao.find(false);
		
		for (Account account : result) {
			logger.trace("Account {} - {} - {} - {} - {}", account.getId(), account.getOwnerName(), account.getBalance(),
					account.getAccessTime(), account.isLocked());
		}

		assertEquals(1, result.size());
	}
	
	@Test
	public void test() {
		assertNotNull(accountDao);
		
		Account account = new Account();
		account.setOwnerName("Joe Smith");
		account.setBalance(20.0);
		account.setAccessTime(new Date());
		account.setLocked(false);
		
		accountDao.insert(account);
		
		assertEquals(2, account.getId());
		
		account = accountDao.find(account.getId());
		
		logger.trace("Account {} - {} - {} - {} - {}", account.getId(), account.getOwnerName(), account.getBalance(),
				account.getAccessTime(), account.isLocked());
		
		account.setBalance(30.0);
		accountDao.update(account);
		
		account = accountDao.find(account.getId());
		logger.trace("Account {} - {} - {} - {} - {}", account.getId(), account.getOwnerName(), account.getBalance(),
				account.getAccessTime(), account.isLocked());
		
		accountDao.delete(account.getId());
		
		List<Account> accounts = accountDao.find(Arrays.asList(account.getId()));
		
		assertEquals(0, accounts.size());
	}

}
