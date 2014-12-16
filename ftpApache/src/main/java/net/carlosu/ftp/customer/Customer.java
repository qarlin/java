package net.carlosu.ftp.customer;

import net.carlosu.ftp.protocol.ProtocolType;


public class Customer {
	private FtpAddress ftpConfiguration;
	private String code;
	private Long   id;

	public FtpAddress getFtpConfiguration() {
		return ftpConfiguration;
	}

	public void setFtpConfiguration(FtpAddress ftpConfiguration) {
		this.ftpConfiguration = ftpConfiguration;
	}

	public ICustomerAddress getAddress(ProtocolType type) {
		switch (type) {
		case FTP:
			return getFtpConfiguration();

		default:
			break;
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
