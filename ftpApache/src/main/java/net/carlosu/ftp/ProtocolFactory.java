package net.carlosu.ftp;

import net.carlosu.ftp.connection.FTPApachePool;
import net.carlosu.ftp.protocol.ApacheFtpPoolClient;
import net.carlosu.ftp.protocol.IDistributeProtocol;
import net.carlosu.ftp.protocol.ILoadProtocol;
import net.carlosu.ftp.protocol.ProtocolType;

public class ProtocolFactory {
	public static IDistributeProtocol getDistributeProtocol(ProtocolType type){
		IDistributeProtocol protocol = null;
		switch (type) {
		case FTP:
			protocol = FtpProtocolFactory.getProtocol(0);
			break;
		default:
			break;
		}
		return protocol;
	}
	
	public static ILoadProtocol getLoadProtocol(ProtocolType type){
		ILoadProtocol protocol = null;
		switch (type) {
		case FTP:
			protocol = FtpProtocolFactory.getProtocol(0);
			break;
		default:
			break;
		}
		return protocol;
	}

	public static IDistributeProtocol getDistributeProtocol(FTPApachePool pool) {
		return new ApacheFtpPoolClient(pool);
	}
}
