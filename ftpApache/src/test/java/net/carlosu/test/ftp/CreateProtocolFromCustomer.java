package net.carlosu.test.ftp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedList;

import net.carlosu.ftp.DistributeService;
import net.carlosu.ftp.customer.Customer;
import net.carlosu.ftp.customer.FtpAddress;
import net.carlosu.ftp.protocol.ProtocolType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class CreateProtocolFromCustomer {
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	
	Customer customer;
	FtpAddress ftpConfiguration;
	FakeFtpServer fakeFtpServer;
	
	protected static final String HOME_DIR = "/";	

	@Before
	public void setUp() throws Exception {
		fakeFtpServer = new FakeFtpServer();
		fakeFtpServer.setServerControlPort(9999); // use any free port
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
		ftpConfiguration.setPort(9999);
		ftpConfiguration.setUser("user");
		ftpConfiguration.setPassword("password");
		ftpConfiguration.setRemoteFolder("/test/");
		customer.setFtpConfiguration(ftpConfiguration);
	}

	@After
	public void tearDown() throws Exception {
		fakeFtpServer.stop();
	}

	@Test
	public void createFTPfromClient() {
		LinkedList<String> files = new LinkedList<>();
		files.add("c:\\Projects\\file1.txt");
		files.add("c:\\Projects\\file2.txt");

		DistributeService ds = new DistributeService();
		ds.distributeDocuments(files, customer, ProtocolType.FTP);
		
		ds.distributeDocuments(files, customer, ProtocolType.FTP);
		
		assertNotNull(ds);
		String[] dfiles = ds.getFileNames(customer, ProtocolType.FTP);
		assertEquals(dfiles.length, 3);
	}
	
	@Test
	public void uploadPoolTwoTimes() {
		LinkedList<String> files = new LinkedList<>();
		files.add("c:\\Projects\\file1.txt");
		files.add("c:\\Projects\\file2.txt");

		DistributeService ds = new DistributeService();
		ds.distributeDocumentsPool(customer, files);
		
		ds.distributeDocumentsPool(customer, files);
		
		assertNotNull(ds);
		String[] dfiles = ds.getFileNames(customer, ProtocolType.FTP);
		assertEquals(dfiles.length, 3);
	}
	
	@Test
	public void ftpCheck() {
		DistributeService ds = new DistributeService();
		String[] files = ds.getFileNames(customer, ProtocolType.FTP);
		assertEquals(files.length, 1);
	}

	@Test
	//@BenchmarkOptions(benchmarkRounds = 50, warmupRounds = 10)
	public void sendContent() {
		HashMap<String, String> files = new HashMap<>();
		files.put("content01.txt", "This is the content for file one");
		files.put("content02.txt", "This is the content for file two");
		files.put("content03.txt", "This is the content for file three");

		DistributeService ds = new DistributeService();
		ds.distributeDocuments(files, customer, ProtocolType.FTP);
		
		ds.downloadFiles("c:\\Projects\\", customer, ProtocolType.FTP);
		
		assertNotNull(ds);
		String[] dfiles = ds.getFileNames(customer, ProtocolType.FTP);
		assertEquals(dfiles.length, 4);
	}
	/*
	 * @Test public void createEmailfromClient() {
	 * 
	 * Protocol emailProtocol = HelperProtocol(client, Protocol.email); }
	 */
}
