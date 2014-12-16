package net.carlosu.ftp.v2.protocol;

import java.util.Collection;
import java.util.HashMap;

import net.carlosu.ftp.customer.FtpAddress;

public interface IFTP<T>{
	public void setAddress(FtpAddress address);
	public void setConnection(FTPConnection<T> connection);

	public String[] getFileNames() throws Exception;
	public void downloadFiles(String localFolder) throws Exception;
	public void downloadFile(String localFolder, String fileName) throws Exception;
	
	public void uploadFiles(Collection<String> files) throws Exception;
	public void uploadContent(HashMap<String, String> content) throws Exception;
	public void uploadFile(String fileName) throws Exception;
	public void uploadContent(String content, String fileName) throws Exception;
}
