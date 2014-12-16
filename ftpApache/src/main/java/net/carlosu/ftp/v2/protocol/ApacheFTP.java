package net.carlosu.ftp.v2.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import net.carlosu.ftp.customer.FtpAddress;

import org.apache.commons.net.ftp.FTPClient;

public class ApacheFTP implements IFTP<FTPClient> {
	protected FTPConnection<FTPClient> connection;
	protected FtpAddress ftpAddress;
	
	@Override
	public void setConnection(FTPConnection<FTPClient> connection) {
		this.connection = connection;
	}

	@Override
	public void setAddress(FtpAddress address) {
		this.ftpAddress = address;
	}
	
	private FTPClient connect() throws Exception {
		return connection.checkOut();
	}
	
	private void disconnect(FTPClient ftp) throws Exception {
		connection.checkIn(ftp);;
	}
	
	@Override
	public String[] getFileNames() throws Exception {
		String[] list = null;
		FTPClient ftp = connect();
		try {
			list = ftp.listNames(ftpAddress.getRemoteFolder());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);
		return list;
	}

	@Override
	public void downloadFiles(String localFolder) throws Exception {
		FTPClient ftp = connect();
		try {
			String[] files = ftp.listNames(ftpAddress.getRemoteFolder());
			for (String fileName : files) {
				OutputStream output = new FileOutputStream(localFolder
						+ fileName);
				ftp.retrieveFile(ftpAddress.getRemoteFolder() + fileName,
						output);
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);
	}

	@Override
	public void uploadFiles(Collection<String> files) throws Exception {
		FTPClient ftp = connect();
		try {
			for (String file : files) {
				uploadFile(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);
	}

	@Override
	public void uploadContent(HashMap<String, String> content) throws Exception {
		try {
			for (Entry<String, String> entry : content.entrySet()) {
				uploadString(entry.getKey(), entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	protected void uploadString(String fileName, String content)
			throws Exception {
		FTPClient ftp = connect();
		OutputStream os = ftp.storeFileStream(ftpAddress.getRemoteFolder()
				+ fileName);

		os.write(content.getBytes());

		os.flush();
		os.close();
		ftp.completePendingCommand();
		disconnect(ftp);
	}

	@Override
	public void uploadFile(String fileName) throws Exception {
		FTPClient ftp = connect();
		try {
			try (InputStream input = new FileInputStream(new File(fileName))) {
				ftp.storeFile(
						ftpAddress.getRemoteFolder()
								+ fileName.substring(
										fileName.lastIndexOf("\\") + 1,
										fileName.length()), input);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);

	}

	@Override
	public void uploadContent(String content, String fileName) throws Exception {
		FTPClient ftp = connect();
		try {
			uploadString(fileName, content);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);

	}

	@Override
	public void downloadFile(String localFolder, String fileName)
			throws Exception {
		FTPClient ftp = connect();
		try {
			OutputStream output = new FileOutputStream(localFolder + fileName);
			ftp.retrieveFile(ftpAddress.getRemoteFolder() + fileName, output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		disconnect(ftp);
	}
}
