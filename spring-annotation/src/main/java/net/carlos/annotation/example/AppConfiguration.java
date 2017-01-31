package net.carlos.annotation.example;

public class AppConfiguration {
	private String db;
	private String url;
	private String user;
	private String pass;
	
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Configuration: \n");
		if (this.db != null) sb.append("Server: " + this.db + "\n");
		if (this.url != null) sb.append("Url: " + this.url + "\n");
		if (this.user != null) sb.append("User: " + this.user + "\n");
		if (this.pass != null) sb.append("Pass: " + this.pass + "\n");
		return sb.toString();
	}
	
}
