package net.carlosu.ftp.protocol;

import net.carlosu.ftp.customer.FtpAddress;
import net.carlosu.ftp.customer.ICustomerAddress;


public abstract class FtpProtocol implements IDistributeProtocol, ILoadProtocol {
	protected FtpAddress ftpAddress;
	
	public void setAddress(ICustomerAddress address){
		if (address instanceof FtpAddress)
			this.ftpAddress = (FtpAddress)address;
	}
}
