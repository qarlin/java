package net.carlosu.ftp.v3.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

import net.carlosu.ftp.v3.connection.ObjectPool;

public class FTPApacheOperations implements FTPOperations {
	
	@Override
	public boolean sendFile(ObjectPool<?> connection, String fileName, String remoteFolder) {
		FTPClient conn = (FTPClient) connection.checkOut();
		try {
			try (InputStream input = new FileInputStream(new File(fileName))) {
				conn.storeFile(
						remoteFolder
								+ fileName.substring(
										fileName.lastIndexOf("\\") + 1,
										fileName.length()), input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
