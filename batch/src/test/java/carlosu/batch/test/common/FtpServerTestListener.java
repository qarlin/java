package carlosu.batch.test.common;

import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.WindowsFakeFileSystem;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class FtpServerTestListener extends AbstractTestExecutionListener {
	FakeFtpServer fakeFtpServer = new FakeFtpServer();
	
	@Override 
	public void beforeTestClass(TestContext testContext) throws Exception {
		/*FtpServer ftpServer = (FtpServer) testContext.getApplicationContext().getBean("ftpServer"); 
		ftpServer.start(); */
		
		fakeFtpServer.setServerControlPort(3200);
		
		fakeFtpServer.addUserAccount(new UserAccount("user", "password", "c:\\data"));

		FileSystem fileSystem = new WindowsFakeFileSystem();
		fileSystem.add(new DirectoryEntry("c:\\data"));
		fileSystem.add(new FileEntry("c:\\data\\file1.txt", "abcdef 1234567890"));
		fileSystem.add(new FileEntry("c:\\data\\run.exe"));
		fakeFtpServer.setFileSystem(fileSystem);

		fakeFtpServer.start();
	} 
	
	@Override 
	public void afterTestClass(TestContext testContext) throws Exception { 
		/*FtpServer ftpServer = (FtpServer) testContext.getApplicationContext().getBean("ftpServer"); 
		ftpServer.stop(); */
		
		fakeFtpServer.stop();
	} 
}
