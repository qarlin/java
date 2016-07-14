package net.carlosu.ftp.v3.factory;

import net.carlosu.ftp.v3.connection.FTPApacheConnectionPool;
import net.carlosu.ftp.v3.connection.ObjectPool;
import net.carlosu.ftp.v3.operations.FTPApacheOperations;
import net.carlosu.ftp.v3.operations.FTPOperations;

public class FTPFactory {
	private static final FTPFactory instance = new FTPFactory();
	
	public static FTPFactory getInstance() {
		return instance;
	}

	public FTPOperations getOperation(ObjectPool<?> ftpConnection) {
		return new FTPApacheOperations();
	}

	public ObjectPool<?> getConnection(String server, String port, String user) {
		ObjectPool<?> o = FTPCacheConnection.getCache(server, port, user);
		return o;
	}

	public ObjectPool<?> newConnection(String server, String port, String user) {
		return new FTPApacheConnectionPool(server, 0, user, null);
	}
}
