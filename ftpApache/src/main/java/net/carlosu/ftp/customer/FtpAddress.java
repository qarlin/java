package net.carlosu.ftp.customer;


public class FtpAddress implements ICustomerAddress{
	private String server;
	private int port;
	private String user;
	private String password;
	private String remoteFolder;
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemoteFolder() {
		return remoteFolder;
	}
	public void setRemoteFolder(String remoteFolder) {
		this.remoteFolder = remoteFolder;
	}
	
	
}
