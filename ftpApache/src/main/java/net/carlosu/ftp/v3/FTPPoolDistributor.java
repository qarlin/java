package net.carlosu.ftp.v3;

import net.carlosu.ftp.v3.connection.ObjectPool;
import net.carlosu.ftp.v3.factory.FTPFactory;
import net.carlosu.ftp.v3.operations.FTPOperations;

public class FTPPoolDistributor {

	public void sendFile(String server, String port, String user, String file, String remoteFolder) {
		ObjectPool<?> ftpConnection = FTPFactory.getInstance().getConnection(server, port, user);
		FTPOperations operation = FTPFactory.getInstance().getOperation(ftpConnection);
		operation.sendFile(ftpConnection, file, remoteFolder);
	}
}
