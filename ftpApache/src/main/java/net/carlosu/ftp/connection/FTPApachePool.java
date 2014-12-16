package net.carlosu.ftp.connection;

import java.io.IOException;

import net.carlosu.ftp.customer.FtpAddress;

import org.apache.commons.net.ftp.FTPClient;


public class FTPApachePool extends ObjectPool<FTPClient> {
	private FtpAddress ftpAddress;
	
	public FTPApachePool(FtpAddress ftpAddress) {
		super();
		this.ftpAddress = ftpAddress;
	}

	@Override
	protected FTPClient create() {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ftpAddress.getServer(), ftpAddress.getPort());	
			ftp.login(ftpAddress.getUser(), ftpAddress.getPassword());
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
				o.logout();
				o.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
