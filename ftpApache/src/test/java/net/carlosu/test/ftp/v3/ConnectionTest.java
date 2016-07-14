package net.carlosu.test.ftp.v3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.carlosu.ftp.v3.FTPPoolDistributor;

public class ConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void sendFile() {
		String server = "1.1.1.1";
		String port = "21";
		String user = "user";
		String file = "file1.zip";
		FTPPoolDistributor ftpPoolDDistributor = new FTPPoolDistributor();
		ftpPoolDDistributor.sendFile(server, port, user, file, null);
	}

}
