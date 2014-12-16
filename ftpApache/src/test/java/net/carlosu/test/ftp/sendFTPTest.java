package net.carlosu.test.ftp;

import static org.junit.Assert.assertEquals;
import net.carlosu.ftp.customer.Customer;
import net.carlosu.ftp.customer.FtpAddress;
import net.carlosu.ftp.v2.protocol.FTPFactory;
import net.carlosu.ftp.v2.protocol.IFTP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

public class sendFTPTest {
	Customer customer;
	FtpAddress ftpConfiguration;
	FakeFtpServer fakeFtpServer;
	
	protected static final String HOME_DIR = "/";	

	@Before
	public void setUp() throws Exception {
		fakeFtpServer = new FakeFtpServer();
		fakeFtpServer.setServerControlPort(9998); // use any free port
		FileSystem fileSystem = new UnixFakeFileSystem();
		fileSystem.add(new FileEntry("/test/file01.txt", "File 01 content"));
		fileSystem.add(new FileEntry("/test/file02.txt", "File 02 content"));
		fakeFtpServer.setFileSystem(fileSystem);
		UserAccount userAccount = new UserAccount("user", "password", HOME_DIR);
		fakeFtpServer.addUserAccount(userAccount);
		fakeFtpServer.start();
		
		customer = new Customer();
		ftpConfiguration = new FtpAddress();
		ftpConfiguration.setServer("localhost");
		ftpConfiguration.setPort(9998);
		ftpConfiguration.setUser("user");
		ftpConfiguration.setPassword("password");
		ftpConfiguration.setRemoteFolder("/test/");
		customer.setFtpConfiguration(ftpConfiguration);
		customer.setId(1L);
	}

	@After
	public void tearDown() throws Exception {
		fakeFtpServer.stop();
	}

	@Test
	public void createFTPfromClient() {
		try {
			IFTP<?> ftp = FTPFactory.getInstance().getFtpProtocol(customer);
			ftp.setAddress(customer.getFtpConfiguration());
			String[] dfiles = ftp.getFileNames();
			assertEquals(dfiles.length, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
