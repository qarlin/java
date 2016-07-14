package net.carlosu.ftp.v3.operations;

import net.carlosu.ftp.v3.connection.ObjectPool;

public interface FTPOperations {

	public boolean sendFile(ObjectPool<?> ftpConnection, String fileName, String remoteFolder);
}
