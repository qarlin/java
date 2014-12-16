package net.carlosu.ftp.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import net.carlosu.ftp.BusinessException;

import org.apache.commons.net.ftp.FTPClient;

public class ApacheFtpClient extends FtpProtocol {
	protected FTPClient ftp;

	public ApacheFtpClient() {
		ftp = new FTPClient();
	}

	protected void connect() throws BusinessException {
		try {
			ftp.connect(ftpAddress.getServer(), ftpAddress.getPort());
			ftp.login(ftpAddress.getUser(), ftpAddress.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	protected void disconnect() throws BusinessException {
		try {
			if (this.ftp.isConnected()) {
				this.ftp.logout();
				this.ftp.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	@Override
	public String[] getFileNames() throws BusinessException {
		String[] list = null;
		connect();
		try {
			list = this.ftp.listNames(ftpAddress.getRemoteFolder());
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		disconnect();
		return list;
	}

	@Override
	public void downloadFiles(String localFolder) throws BusinessException {
		connect();
		try {
			String[] files = this.ftp.listNames(ftpAddress.getRemoteFolder());
			for (String fileName : files) {
				OutputStream output = new FileOutputStream(localFolder
						+ fileName);
				ftp.retrieveFile(ftpAddress.getRemoteFolder() + fileName,
						output);
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		disconnect();
	}

	@Override
	public void uploadFiles(Collection<String> files) throws BusinessException {
		connect();
		try {
			for (String file : files) {
				try (InputStream input = new FileInputStream(new File(file))) {
					this.ftp.storeFile(
							ftpAddress.getRemoteFolder()
									+ file.substring(
											file.lastIndexOf("\\") + 1,
											file.length()), input);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		disconnect();
	}

	@Override
	public void uploadContent(HashMap<String, String> content)
			throws BusinessException {
		connect();
		try {
			for (Entry<String, String> entry : content.entrySet()) {
				uploadString(entry.getKey(), entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		disconnect();
	}

	protected void uploadString(String fileName, String content)
			throws IOException {
		OutputStream os = this.ftp.storeFileStream(ftpAddress.getRemoteFolder()
				+ fileName);

		/*final int aLength = content.length();
		final int aChunk = 4096;
		final char[] aChars = new char[aChunk];

		for (int aPosStart = 0; aPosStart < aLength; aPosStart += aChunk) {
			final int aPosEnd = Math.min(aPosStart + aChunk, aLength);
			content.getChars(aPosStart, aPosEnd, aChars, 0); // Create no new buffer
			final CharArrayReader aCARead = new CharArrayReader(aChars);
			// Create no new buffer

			// This may be slow but it will not create any more buffer (for bytes)
			int aByte;
			while ((aByte = aCARead.read()) != -1)
				os.write(aByte);
		}*/

		os.write(content.getBytes());

		os.flush();
		os.close();
		this.ftp.completePendingCommand();
	}
}
