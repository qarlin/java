package net.carlosu.ftp.v2.protocol;

import java.io.IOException;
import java.net.SocketException;

import net.carlosu.ftp.customer.FtpAddress;

public abstract class FTPConnection<T> {
	protected IConnection<T> 	conn;
	protected FtpAddress		ftpAddress;
	
	public void setConnection(IConnection<T> conn){
		this.conn = conn;
	}
	
	public void setAddress(FtpAddress address){
		this.ftpAddress = address;
	}
	
	public abstract T checkOut() throws SocketException, IOException;
	public abstract void checkIn(T t);
}
