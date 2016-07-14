package net.carlosu.ftp.v3.connection;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPApacheConnectionPool extends ObjectPool<FTPClient> {
	private String ftpAddress;
	private String ftpUser;
	private String ftpPassword;
	private int ftpPort;
	
	public FTPApacheConnectionPool(String ftpAddres, int ftpPort, String ftpUser, String ftpPassword){
		this.ftpAddress = ftpAddres;
		this.ftpUser = ftpUser;
		this.ftpPassword = ftpPassword;
	}
	
	@Override
	protected FTPClient create() {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ftpAddress, ftpPort);	
			ftp.login(ftpUser, ftpPassword);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return ftp;
	}

	@Override
	public boolean validate(FTPClient o) {
		return o.isConnected();
	}

	@Override
	public void expire(FTPClient o) {
		try {
			if (o.isConnected()) {
				o.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
