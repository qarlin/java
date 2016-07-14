package net.carlosu.ftp.v2.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.carlosu.ftp.customer.Customer;
import net.carlosu.ftp.v3.connection.ObjectPool;

import org.apache.commons.net.ftp.FTPClient;

public class FTPFactory {
	private String ftpClient;
	private boolean usePooling;
	private boolean useCache;
	private static final FTPFactory instance = new FTPFactory();
	
	public static FTPFactory getInstance() {
		return instance;
	}
	private FTPFactory(){
		loadConfiguration();
	}
	//Generic FTP Protocol
	public FTPConnection<?> getConnection(Customer customer) {
		return getConnection(customer, useCache);
	}

	public FTPConnection<?> getConnection(Customer customer, boolean useCache) {
		if ("apache".equals(ftpClient)) {
			return getApacheFTPConnection(customer, usePooling, useCache);
		} 
		return null;
	}
	//Apache Client
	@SuppressWarnings("unchecked")
	private FTPConnection<FTPClient> getApacheFTPConnection(Customer customer, boolean usePooling, boolean useCache) {
		IConnection<FTPClient> conn = new ApacheConnection();
		FTPConnection<FTPClient> ftpConn;
		if (usePooling){
			if (useCache)
				ftpConn = (FTPConnection<FTPClient>) CacheConnection.getCache(customer);
			else
				ftpConn = new FTPPool<FTPClient>();
		} else
			ftpConn = new FTPBatch<FTPClient>();
		ftpConn.setAddress(customer.getFtpConfiguration());
		ftpConn.setConnection(conn);
		return ftpConn;
	}
	
	private void loadConfiguration(){
		Properties p = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/META-INF/FtpConfig.properties");
		try {
			p.load(is);
			setProperties(p);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setProperties(Properties p){
		ftpClient = p.getProperty("server");
		usePooling = Boolean.valueOf(p.getProperty("pooling"));
		useCache = Boolean.valueOf(p.getProperty("cache"));
	}
	
	public IFTP<FTPClient> getApacheFtpProtocol(Customer customer) {
		FTPConnection<FTPClient> conn = getApacheFTPConnection(customer, usePooling, useCache);
		IFTP<FTPClient> ftp = new ApacheFTP();
		ftp.setAddress(customer.getFtpConfiguration());
		ftp.setConnection(conn);
		return ftp;
	}
	public IFTP<?> getFtpProtocol(Customer customer) {
		if ("apache".equals(ftpClient)) {
			return getApacheFtpProtocol(customer);
		}
		return null;
	}
	public ObjectPool<?> getConnection(String server, String port, String user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
