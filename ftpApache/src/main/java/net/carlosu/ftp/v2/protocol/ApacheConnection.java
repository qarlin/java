package net.carlosu.ftp.v2.protocol;

import java.io.IOException;

import net.carlosu.ftp.customer.FtpAddress;

import org.apache.commons.net.ftp.FTPClient;

public class ApacheConnection implements IConnection<FTPClient> {

	@Override
	public FTPClient create(FtpAddress ftpAddress) {
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
				o.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
