package net.carlosu.ftp;

import net.carlosu.ftp.protocol.ApacheFtpClient;
import net.carlosu.ftp.protocol.FtpProtocol;

public class FtpProtocolFactory {

	public static FtpProtocol getProtocol(int i) {
		return new ApacheFtpClient();
	}

}
