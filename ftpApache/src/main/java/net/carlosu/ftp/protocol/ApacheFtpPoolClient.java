package net.carlosu.ftp.protocol;

import net.carlosu.ftp.BusinessException;
import net.carlosu.ftp.connection.FTPApachePool;


public class ApacheFtpPoolClient extends ApacheFtpClient{
	FTPApachePool pool;
	
	public ApacheFtpPoolClient(FTPApachePool pool) {
		this.pool = pool;
	}
	
	protected void connect() throws BusinessException {
		this.ftp = pool.checkOut();
	}

	protected void disconnect() throws BusinessException {
		pool.checkIn(this.ftp);
	}
}
